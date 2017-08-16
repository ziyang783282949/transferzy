package com.spirit.porker.agent;

import java.util.Map;

import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.GetOpenIdRes;
import com.spirit.porker.vo.response.UserInfoRes;
import com.spirit.porker.vo.response.WxUnifiedOrderResponse;

public interface IOpenService {
	
	/**
	 * 获取登录状态信息
	 */
	public String validateLoginStatus(Map<String, Object> pojo);

	/**
	 * 获取第三方登录后accesstoken信息
	 */
	public String getAccessToken(Map<String, Object> pojo);
	
	/**
	 * 获取第三方登录后openId信息
	 */
	public BaseResponse<GetOpenIdRes> getOpenId(Map<String, Object> pojo);
	
	
	/**
	 * 获取第三方登录后用户详细信息
	 */
	public UserInfoRes getUserDetail(Map<String, Object> pojo);
	
	/**
	 * 向商户发起支付请求生成支付方订单号信息
	 * @param pojo
	 * @return
	 */
	public BaseResponse<WxUnifiedOrderResponse> pay(Map<String, Object> pojo);
	
}


