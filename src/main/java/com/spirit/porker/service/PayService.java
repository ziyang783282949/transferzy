package com.spirit.porker.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import com.spirit.porker.agent.IOpenService;
import com.spirit.porker.agent.UionOpenApiFactory;
import com.spirit.porker.dao.EventDao;
import com.spirit.porker.dao.OrderDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.AgentMerchantType;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.model.EventModel;
import com.spirit.porker.model.OrderModel;
import com.spirit.porker.util.PropertyUtil;
import com.spirit.porker.vo.request.PayRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.WxUnifiedOrderResponse;

@Service
public class PayService {
	
	@Resource
	OrderDao orderDao;

	@Resource
	EventDao eventDao;

	
	public BaseResponse<WxUnifiedOrderResponse> continuePay(PayRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<WxUnifiedOrderResponse> result = new BaseResponse<WxUnifiedOrderResponse>(ResultType.succes);
		WxUnifiedOrderResponse data = new WxUnifiedOrderResponse();
		result.setData(data);
		
		IOpenService iOpenService = UionOpenApiFactory.getServiceByType(AgentMerchantType.wechat.getCode());
		
		Map<String, Object> cond = new HashMap<String,Object>();
		cond.put("orderId", pojo.getOrderId());
		PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
		if(orders == null || orders.size() > 0){
			result.setCode(ResultType.nonOrderEvent.getCode());
			result.setDesc(ResultType.nonExistEvent.getDesc());
			return result;
		}
		
		cond.clear();
		cond.put("eventId", orders.get(0).getEventId());
		PaginationList<EventModel> events = eventDao.findEntityListByCond(cond, null);
		if(events == null || events.size() > 0){
			result.setCode(ResultType.nonExistEvent.getCode());
			result.setDesc(ResultType.nonExistEvent.getDesc());
			return result;
		}
		
		Map<String, Object> payReq = new HashMap<String,Object>();
		payReq.put("orderId", pojo.getOrderId());
		payReq.put("notifyUrl", PropertyUtil.config.getProperties("wx_pay_notify_url"));
		payReq.put("eventInfo", events.get(0).getName());
		payReq.put("openId", pojo.getOpenId());
		
		BaseResponse<WxUnifiedOrderResponse> wxUnifiedOrderResponse = iOpenService.pay(cond);
		if(wxUnifiedOrderResponse != null && wxUnifiedOrderResponse.getCode() != ResultType.succes.getCode()){
			result.setCode(ResultType.fail.getCode());
			result.setDesc("唤起微信支付异常，请稍后重试或联系客服。");
		}
		
		return result;
	}
	
	

}


