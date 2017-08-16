package com.spirit.porker.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.stax2.ri.typed.ValueEncoderFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.EventDao;
import com.spirit.porker.dao.OrderDao;
import com.spirit.porker.dao.UserDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.EnrollStatus;
import com.spirit.porker.enums.EventDetailBtnStatus;
import com.spirit.porker.enums.EventStatus;
import com.spirit.porker.enums.EventType;
import com.spirit.porker.enums.OrderStatus;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.model.EventModel;
import com.spirit.porker.model.OrderModel;
import com.spirit.porker.util.DateUtil;
import com.spirit.porker.util.PropertyUtil;
import com.spirit.porker.vo.request.EventDetailRequest;
import com.spirit.porker.vo.request.EventListRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.Blind;
import com.spirit.porker.vo.response.EventDetailResponse;
import com.spirit.porker.vo.response.EventResponse;
import com.spirit.porker.vo.response.MerchantInfo;
import com.spirit.porker.vo.response.Player;
import com.spirit.porker.vo.response.RewardInfo;
import com.spirit.porker.vo.response.RewardPlayer;

@Service
public class EventService {
	
	@Resource
	EventDao eventDao;
	
	@Resource
	UserDao userDao;
	
	@Resource
	OrderDao orderDao;
	
	
	/**
	 * 获取赛事列表
	 * 按照UI上要求：赛事列表上当当前用户已经报名时，需要展示已报名状态。
	 * @param pojo
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResponse<List<EventResponse>> getEvents(EventListRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<List<EventResponse>> result = new BaseResponse<List<EventResponse>>(ResultType.succes);
		List<EventResponse> data = new ArrayList<EventResponse>();
		result.setData(data);
		
		//查询赛事基本信息
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("beginTime", DateUtil.getTwoDaysBefore());
		PaginationList<EventModel> models = eventDao.findEntityListByCond(cond, null);
		if( models==null || models.size() == 0){
			return result;
		}
		
		for(int i=0;i<models.size();i++){
			EventResponse vo = new EventResponse();
			
			List<Player> curPlayers = new ArrayList<>();
			if(!StringUtils.isBlank(models.get(i).getCurPlayers())){
				curPlayers = JSON.parseArray(models.get(i).getCurPlayers(),Player.class);
			}
			
			vo.setId(models.get(i).getId());
			vo.setName(models.get(i).getName());
			vo.setEventDay(DateUtil.getMD(models.get(i).getEventTime()));
			
			String[] hm=DateUtil.getHm(models.get(i).getEventTime()).split(":");
			if(Integer.parseInt(hm[0])<=12) {
				vo.setEventTime("上午 "+hm[0]+":"+hm[1]);
			}
			else {
				vo.setEventTime("下午 "+hm[0]+"时"+hm[1]);
			}
			vo.setPrice(models.get(i).getPrice().add(models.get(i).getServiceFee()));
			vo.setEventStatus(models.get(i).getEventStatus());
			vo.setPlayers(curPlayers.size()+"/"+models.get(i).getMaxPlayers()+"（最低人数"+models.get(i).getMinPlayers()+"人)");
			if(models.get(i).getEventType()==EventType.day.getCode()) {
				vo.setImgUrl(PropertyUtil.config.getProperties("day"));
			}
			if(models.get(i).getEventType()==EventType.week.getCode()) {
				vo.setImgUrl(PropertyUtil.config.getProperties("week"));
			}
			if(models.get(i).getEventType()==EventType.other.getCode()) {
				vo.setImgUrl(PropertyUtil.config.getProperties("other"));
			}
			if(models.get(i).getEventStatus()==EventStatus.canceled.getCode()) {
				vo.setStatusImgUrl(PropertyUtil.config.getProperties("canceled"));
			}
			if(models.get(i).getEventStatus()==EventStatus.doing.getCode()) {
				vo.setStatusImgUrl(PropertyUtil.config.getProperties("doing"));
			}
			if(models.get(i).getEventStatus()==EventStatus.done.getCode()) {
				vo.setStatusImgUrl(PropertyUtil.config.getProperties("done"));
			}
			if(models.get(i).getEventStatus()==EventStatus.init.getCode()) {
				vo.setStatusImgUrl(PropertyUtil.config.getProperties("init"));
			}
			/*vo.setCurPlayers(buildPlayers(curPlayers));
			vo.setCurPlayersNum(curPlayers.size());
			
			BigDecimal floatPot = models.get(i).getPrice().multiply(new BigDecimal(curPlayers.size()));
			BigDecimal curPot = models.get(i).getBasePot().add(floatPot);
			
			vo.setCurPot(curPot);
			vo.setEventHourMin(DateUtil.getHm(models.get(i).getEventTime()));*/
			
			//确认当前用户所报名的信息
			for(int k=0;k<curPlayers.size();k++){
				if(curPlayers.get(k).getOpenId().equals(pojo.getOpenId())){
					vo.setEnrollStatus(EnrollStatus.in.getCode());
					//vo.setEnrollStatusName(EnrollStatus.in.getDesc());
				}
			}
			
			//vo.setEventStatusName(EventStatus.getTypeByCode(models.get(i).getEventStatus()).getDesc());
			
			data.add(vo);
		}
		
		return result;
		
	}
	
	/**
	 * 构建给前端展示报名用户的信息字段
	 * @param list
	 * @return
	 */
	public String buildPlayers(List<Player> list){
		
		if(list == null  || list.size() == 0){
			return null;
		}
		
		StringBuilder sBuilder = new StringBuilder();
		for(int i=0;i<list.size();i++){
			sBuilder.append(list.get(i).getNickName());
			sBuilder.append(",");
		}
		
		sBuilder.replace(sBuilder.length()-1, sBuilder.length(), "");
		return sBuilder.toString();
	}
	
	/**
	 * 获取赛事详情信息
	 * @param pojo
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResponse<EventDetailResponse> getEventDetail(EventDetailRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<EventDetailResponse> result = new BaseResponse<EventDetailResponse>(ResultType.succes);
		EventDetailResponse data = new EventDetailResponse();
		result.setData(data);
		
		//查询赛事基本信息
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("id", pojo.getEventId());
		PaginationList<EventModel> models = eventDao.findEntityListByCond(cond, null);
		if( models==null || models.size() == 0){
			return result;
		}
		
		//赛事时间相关
		EventModel model = models.get(0);
		data.setEventTime(model.getEventTime().toString());
		data.setEventDay(DateUtil.getYMD(model.getEventTime()));
		data.setEventTime(DateUtil.getHm(model.getEventTime()));
		data.setEventName(model.getName());
		
		//设置赛事状态图片
		if(model.getEventStatus()==EventStatus.canceled.getCode()) {
			data.setStatusImgUrl(PropertyUtil.config.getProperties("canceled"));
		}
		if(model.getEventStatus()==EventStatus.doing.getCode()) {
			data.setStatusImgUrl(PropertyUtil.config.getProperties("doing"));
		}
		if(model.getEventStatus()==EventStatus.done.getCode()) {
			data.setStatusImgUrl(PropertyUtil.config.getProperties("done"));
		}
		if(model.getEventStatus()==EventStatus.init.getCode()) {
			data.setStatusImgUrl(PropertyUtil.config.getProperties("init"));
		}
		List<Player> curPlayers = new ArrayList<>();
		if(!StringUtils.isBlank(model.getCurPlayers())){
			curPlayers = JSON.parseArray(model.getCurPlayers(),Player.class);
		}
		
		//状态相关信息
		Player curPlayer = null;
		for(int k=0;k<curPlayers.size();k++){
			if(curPlayers.get(k).getOpenId().equals(pojo.getOpenId())){
				curPlayer = curPlayers.get(k);
				data.setEnrollStatus(EnrollStatus.in.getCode());
				data.setEnrollStatusName(EnrollStatus.in.getDesc());
				data.setRebuyTimes(curPlayers.get(k).getRebuyTimes());
				data.setRebuyUpperTimes(2);
				break;
			}
		}
		
		data.setEventStatus(model.getEventStatus());
		data.setEventStatusName(EventStatus.getTypeByCode(model.getEventStatus()).getDesc());
		
		//报名用户信息
		data.setCurPlayers(curPlayers);
		data.setCurPlayersNum(curPlayers.size());
		BigDecimal floatPot = model.getPrice().multiply(new BigDecimal(curPlayers.size()));
		BigDecimal curPot = model.getBasePot().add(floatPot).setScale(0, BigDecimal.ROUND_HALF_UP);
		data.setCurPot(new DecimalFormat(",###").format(curPot));
		data.setPrice(model.getPrice().add(model.getServiceFee()));
		
		//赛事地理位置信息和电话信息
		MerchantInfo merchantInfo = new MerchantInfo();
		merchantInfo.setTel(PropertyUtil.config.getProperties("merchant_tel"));
		merchantInfo.setLatitude(PropertyUtil.config.getProperties("merchant_latitude"));
		merchantInfo.setLongitude(PropertyUtil.config.getProperties("merchant_longtitude"));
		data.setMerchantInfo(merchantInfo);
		
		//盲注级别
		data.setBlindInfo(JSON.parseArray(model.getBlindInfo(),Blind.class));
		
		//赛事奖励信息和赛事结束后的名次信息
		data.setRewardInfo(JSON.parseArray(model.getRewardInfo(),RewardInfo.class));
		data.setRewardPlayers(JSON.parseArray(model.getRewardPlayers(),RewardPlayer.class));
		
		//获取当前用户在该场赛事中的买入次数
		if(StringUtils.isBlank(curPlayer.getOrderId())){
			cond.clear();
			cond.put("orderId", curPlayer.getOrderId());
			PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
			if(orders != null && orders.size() == 0){
				data.setRebuyTimes(orders.get(0).getRebuyTimes());
			}
		}
		
		data.setOperType(getOperType(model.getEnrollCloseTime(),curPlayer.getRebuyTimes(),model.getEventStatus(),
				curPlayer!=null?EnrollStatus.in.getCode():EnrollStatus.notIn.getCode()).getCode());
		
		return result;
	}
	
	/**
	 * 未报名：
		赛事已结束：不展示按钮栏
		到截止时间：不展示按钮栏
		未到截止时间：立即报名
	   已报名：
		赛事已结束：不展示按钮栏
		未开始：置灰rebuy按钮
		超过rebuy次数：不展示按钮栏
		未超过rebuy次数：rebuy按钮
		截止时间：不考虑
	 * @param eventTime
	 * @param rebuyTimes
	 * @param eventStatus
	 * @param enrollStatus
	 * @return
	 */
	public EventDetailBtnStatus getOperType(Timestamp enrollCloseTime,int rebuyTimes,int eventStatus,int enrollStatus){
		
		Date closeTime = new Date(enrollCloseTime.getTime());
		Calendar now = Calendar.getInstance();
		
		if(enrollStatus == EnrollStatus.notIn.getCode()){
			if(eventStatus == EventStatus.done.getCode()){
				return EventDetailBtnStatus.hidden;
			}
			if( now.getTime().compareTo(closeTime) >=0){
				return EventDetailBtnStatus.hidden;
			}
			return EventDetailBtnStatus.toEnroll;
		}
		
		if(eventStatus == EventStatus.done.getCode()){
			return EventDetailBtnStatus.hidden;
		}
		
		if(eventStatus == EventStatus.init.getCode()){
			return EventDetailBtnStatus.greyRebuy;
		}
		
		if(rebuyTimes > 2){
			return EventDetailBtnStatus.hidden;
		}
		return EventDetailBtnStatus.toRebuy;
	}

}
