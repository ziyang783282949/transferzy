package com.spirit.porker.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.EventDao;
import com.spirit.porker.dao.OrderDao;
import com.spirit.porker.dao.OrderIdGenerator;
import com.spirit.porker.dao.UserDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.OrderStatus;
import com.spirit.porker.enums.PayType;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.event.UpdateEventEvent;
import com.spirit.porker.message.UpdateEventAfterPayMessage;
import com.spirit.porker.model.EventModel;
import com.spirit.porker.model.OrderModel;
import com.spirit.porker.model.UserModel;
import com.spirit.porker.util.DateUtil;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.EventMisc;
import com.spirit.porker.vo.request.EventDetailRequest;
import com.spirit.porker.vo.request.OrderDetailRequest;
import com.spirit.porker.vo.request.OrderListRequest;
import com.spirit.porker.vo.request.SubmitOrderRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.EventDetailResponse;
import com.spirit.porker.vo.response.OrderDetailResponse;
import com.spirit.porker.vo.response.OrderListResponse;
import com.spirit.porker.vo.response.Player;
import com.spirit.porker.vo.response.SumitOrderResponse;

/**
 * @Description:订单相关操作，提交订单等需增加事务控制
 * @author: tony.wang
 * @time:2017年7月28日 下午5:13:50
 */
@Service
public class OrderService {
	
	@Resource
	EventDao eventDao;
	
	@Resource
	OrderDao orderDao;
	
	@Resource
	UserDao userDao;
	
	@Resource
	EventService eventService;
	
	
	@Resource
	OrderIdGenerator orderIdGenerator;
	
	@Resource
	ApplicationContext applicationContext;
	
	public BaseResponse<SumitOrderResponse> create(SubmitOrderRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<SumitOrderResponse> result = new BaseResponse<SumitOrderResponse>(ResultType.succes);
		SumitOrderResponse data = new SumitOrderResponse();
		result.setData(data);
		
		//赛事信息校验（是否存在，是否过了报名期，是否有未支付的订单）
		Map<String, Object> cond = new HashMap<String,Object>();
		cond.put("id", pojo.getEventId());
		PaginationList<EventModel> events = eventDao.findEntityListByCond(cond, null);
		if(events == null || events.size() == 0){
			result.setCode(ResultType.nonExistEvent.getCode());
			result.setDesc(ResultType.nonExistEvent.getDesc());
			return result;
		}
		
		if(events.get(0).getEnrollCloseTime().compareTo(new Date()) <0){
			result.setCode(ResultType.nonExistEvent.getCode());
			result.setDesc(ResultType.nonExistEvent.getDesc());
			return result;
		}
		
		//是否有未完成的订单
		if(getUncompleteOrder(pojo.getOpenId())){
			result.setCode(ResultType.unCompleteOrder.getCode());
			result.setDesc(ResultType.unCompleteOrder.getDesc());
			return result;
		}
		
		//生成订单
		OrderModel orderModel = new OrderModel();
		orderModel.setId(orderIdGenerator.getOrderId());
		orderModel.setCardNo(pojo.getCardNo());
		orderModel.setOrderTime(new Timestamp(System.currentTimeMillis()));
		orderModel.setEventId(pojo.getEventId());
		orderModel.setOrderStatus(OrderStatus.init.getCode());
		orderModel.setOrderAmount(events.get(0).getPrice().add(events.get(0).getServiceFee()));
		orderModel.setPayType(pojo.getPayType());
		EventMisc eventMisc = new EventMisc();
		eventMisc.setEventName(events.get(0).getName());
		eventMisc.setEventTime(DateUtil.convertHHmmdd(events.get(0).getEventTime()));
		orderModel.setEventMisc(JSON.toJSONString(eventMisc));
		orderDao.addEntity(orderModel);
		
		//更新赛事中已报名用户信息和奖池总额，在支付成功之后
		
		cond.clear();
		PaginationList<UserModel> users = userDao.findEntityListByCond(cond, null);
		if(users == null || users.size() == 0){
			result.setCode(ResultType.unKnowUser.getCode());
			result.setDesc(ResultType.unKnowUser.getDesc());
			return result;
		}
		
		/**按照支付类型，采取不同的策略*/
		/**1. 积分支付时，检查用户积分余额*/
		if(pojo.getPayType().intValue() == PayType.point.getCode()){
			
			BigDecimal amount = events.get(0).getPrice().add(events.get(0).getServiceFee());
			if(amount.intValue() > users.get(0).getScore()){
				result.setCode(ResultType.nonEnough.getCode());
				result.setDesc(ResultType.nonEnough.getDesc());
				return result;
			}
			//触发异步减去用户积分余额、更新赛事中已报名用户信息和奖池总额。
			UpdateEventAfterPayMessage message = new UpdateEventAfterPayMessage();
			message.setCardNo(pojo.getCardNo());
			message.setEventId(pojo.getEventId());
			message.setOrderId(orderModel.getId());
			message.setPayType(PayType.point.getCode());
			applicationContext.publishEvent(new UpdateEventEvent(message));
			
			return result;
		}
		/**2. 使用现金支付，唤起微信支付。*/
		
		
		return result;
	}
	
	/**
	 * 校验是否由未完成订单
	 * @param cardNo
	 * @return true 是 false 没有
	 */
	public boolean getUncompleteOrder(String cardNo){
		
		Map<String, Object> cond = new HashMap<String,Object>();
		cond.put("cardNo", cardNo);
		cond.put("orderStatus", OrderStatus.init.getCode());
		PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
		
		if(orders != null && orders.size() >0){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 更新赛事中已报名用户信息、奖池总额，在支付成功之后
	 * 需要考虑消息执行两次造成的错误数据
	 * 比如，两个用户同时报名时，同时执行到当前方法，如果不加控制，就会出现脏读的情况。
	 * (1) 对event表加行级锁？
	 * (2) 对当前方法增加锁
	 * 
	 */
	public void updateEventAfterPay(UpdateEventAfterPayMessage  message){
		
		Map<String, Object> cond = new HashMap<String,Object>();
		cond.put("orderId", message.getOrderId());
		PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
		if(orders == null || orders.size() == 0){
			LoggerUtil.message("更新无效的orderId，orderId="+message.getOrderId(), "", "", JSON.toJSONString(message));
			return;
		}
		
		cond.put("id", orders.get(0).getEventId());
		/**0. 验证赛事是否存在*/
		PaginationList<EventModel> events = eventDao.findEntityListByCond(cond, null);
		if(events == null || events.size() == 0){
			LoggerUtil.message("更新无效的eventId，id="+message.getEventId(), "", "", JSON.toJSONString(message));
			return;
		}
		
		/**1. 查询该用户是否已经处理过了。判定cardNo，防止重复添加*/
		List<Player> curPlayers = new ArrayList<>();
		if(!StringUtils.isBlank(events.get(0).getCurPlayers())){
			curPlayers = JSON.parseArray(events.get(0).getCurPlayers(),Player.class);
		}
		
		/**扣除当前用户积分*/
		cond.clear();
		cond.put("cardNo", orders.get(0).getCardNo());
		PaginationList<UserModel> users =  userDao.findEntityListByCond(cond, null);
		if(users == null || users.size() ==0){
			return ;
		}
		
		UserModel userModel = new UserModel();
		userModel.setCardNo(message.getCardNo());
		BigDecimal payAmount = events.get(0).getPrice().add(events.get(0).getServiceFee());
		userModel.setScore(users.get(0).getScore() - payAmount.intValue());
		userModel.setBuyIn(userModel.getBuyIn() + payAmount.intValue());
		userDao.updateEntity(userModel);
		
		/**3.更新当前赛事的会员信息*/
		Player newPlayer = new Player();
		newPlayer.setCardNo(message.getCardNo());
		newPlayer.setNickName(users.get(0).getNickName());
		newPlayer.setOrderId(message.getOrderId());
		curPlayers.add(newPlayer);
		
		EventModel entity = new EventModel();
		entity.setId(events.get(0).getId());
		entity.setCurPlayers(JSON.toJSONString(curPlayers));
		BigDecimal newPot = events.get(0).getBasePot().add(events.get(0).getPrice());
		entity.setBasePot(newPot);
		
		eventDao.updateEntity(entity);
		
		/**4. 更新订单状态为支付成功*/
		OrderModel orderModel = new OrderModel();
		orderModel.setId(message.getOrderId());
		orderModel.setOrderStatus(OrderStatus.joined.getCode());
		orderModel.setPaySuccessTime(new Timestamp(System.currentTimeMillis()));
		orderModel.setPayAmount(payAmount);
		orderModel.setPayType(message.getPayType());
		orderDao.updateEntity(orderModel);
		
	}
	
	/**
	 * 获取订单列表(一参赛订单，只保留一周的订单信息，超过一周时间不展示)
	 * @param pojo
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResponse<List<OrderListResponse>> orderList(OrderListRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<List<OrderListResponse>> result = new BaseResponse<List<OrderListResponse>>(ResultType.succes);
		List<OrderListResponse> datas = new ArrayList<OrderListResponse>();
		result.setData(datas);
		
		Map<String,Object> cond = new HashMap<String,Object>();
		cond.put("cardNo", pojo.getCardNo());
		cond.put("orderStatus", pojo.getType());
		PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
		if(orders == null || orders.size() == 0){
			return result;
		}
		
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0;i<orders.size();i++){
			ids.add(orders.get(i).getEventId());
		}
		
		cond.clear();
		cond.put("ids", ids);
		PaginationList<EventModel> events = eventDao.findEntityListByCond(cond, null);
		if(events == null || events.size() ==0){
			return result;
		}

		//重新匹配结果
		for(int i=0;i<orders.size();i++){
			OrderListResponse vo = new OrderListResponse(); 
			vo.setOrderTime(orders.get(i).getOrderTime().toString());
			vo.setOrderId(orders.get(i).getId());
			vo.setPrice(orders.get(i).getOrderAmount());
			
			for(int k=0;k<events.size();k++){
				if(orders.get(i).getEventId() == events.get(k).getId().intValue()){
					vo.setEventName(events.get(k).getName());
					vo.setEventBeginTime(events.get(k).getEventTime().toString());
					List<Player> curPlayers = JSON.parseArray(events.get(k).getCurPlayers(),Player.class);
					StringBuilder sBuilder = new StringBuilder();
					sBuilder.append(curPlayers.size());
					sBuilder.append("/");
					sBuilder.append(events.get(k).getMaxPlayers());
					sBuilder.append("（最低人数"+events.get(k).getMinPlayers()+"）");
					vo.setPlayers(sBuilder.toString());
					vo.setEventId(events.get(k).getId().intValue());
					
					if(events.get(k).getEnrollCloseTime().compareTo(new Date()) > 0){
						vo.setCanPay(1);
					}
					
					break;
				}
			}
			
			datas.add(vo);
		}
		
		result.setData(datas);
		return result;
	}
	
	/**
	 * 获取订单详情
	 * @param pojo
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResponse<OrderDetailResponse> orderDetail(OrderDetailRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<OrderDetailResponse> result = new BaseResponse<OrderDetailResponse>(ResultType.succes);
		OrderDetailResponse data = new OrderDetailResponse();
		result.setData(data);
		
		Map<String,Object> cond = new HashMap<String,Object>();
		
		cond.put("orderId", pojo.getOrderId());
		PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
		if(orders == null || orders.size() == 0){
			result.setCode(ResultType.nonOrderEvent.getCode());
			result.setDesc(ResultType.nonOrderEvent.getDesc());
			return result;
		}
		
		EventDetailRequest eventDetailRequest = new EventDetailRequest();
		eventDetailRequest.setEventId(orders.get(0).getEventId());
		
		BaseResponse<EventDetailResponse> eventDetail = eventService.getEventDetail(eventDetailRequest, null, null);
		if(eventDetail.getCode() != ResultType.succes.getCode()){
			result.setCode(eventDetail.getCode());
			result.setDesc(eventDetail.getDesc());
			return result;
		}
		
		data.setEventDetail(eventDetail.getData());
		data.setOrderStatus(orders.get(0).getOrderStatus());
		
		return result;
	}
	
	/**
	 * 用户rebuy
	 * @param pojo
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResponse<OrderDetailResponse> rebuy(OrderDetailRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<OrderDetailResponse> result = new BaseResponse<OrderDetailResponse>(ResultType.succes);
		OrderDetailResponse data = new OrderDetailResponse();
		result.setData(data);
		
		Map<String,Object> cond = new HashMap<String,Object>();
		
		cond.put("orderId", pojo.getOrderId());
		PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
		if(orders == null || orders.size() == 0){
			result.setCode(ResultType.nonOrderEvent.getCode());
			result.setDesc(ResultType.nonOrderEvent.getDesc());
			return result;
		}
		
		
		
		return result;
	}
}


