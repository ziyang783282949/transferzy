package com.spirit.porker.agent.wechat.service;

import java.util.Map;
import java.util.UUID;

import com.spirit.porker.agent.IOpenService;
import com.spirit.porker.agent.wechat.request.GetOpenIdRequest;
import com.spirit.porker.agent.wechat.request.WxUnifiedOrderRequest;
import com.spirit.porker.agent.wechat.response.GetWxOpenIdRes;
import com.spirit.porker.agent.wechat.response.WxUnifiedOrderRes;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.util.HttpsUtils;
import com.spirit.porker.util.PropertyUtil;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.GetOpenIdRes;
import com.spirit.porker.vo.response.UserInfoRes;
import com.spirit.porker.vo.response.WxUnifiedOrderResponse;

/**
 * @Description:微信相关服务
 * @author: tony.wang
 * @time:2017年8月2日 上午10:24:56
 */
public class WechatService implements IOpenService{

	@Override
	public String validateLoginStatus(Map<String, Object> pojo) {
		
		return null;
	}

	@Override
	public String getAccessToken(Map<String, Object> pojo) {
		return null;
	}

	@Override
	public BaseResponse<GetOpenIdRes> getOpenId(Map<String, Object> pojo) {
		
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		
		GetOpenIdRequest req = new GetOpenIdRequest();
		req.setAppid(PropertyUtil.config.getProperties("appid"));
		req.setJs_code(pojo.get("code").toString());
		req.setSecret(PropertyUtil.config.getProperties("appid_secret"));
		
		GetWxOpenIdRes wxRes = HttpsUtils.get(url, req, 5000, GetWxOpenIdRes.class);
		
		BaseResponse<GetOpenIdRes> result = new BaseResponse<GetOpenIdRes>(ResultType.succes);
		GetOpenIdRes data = new GetOpenIdRes();
		result.setData(data);
		
		if(wxRes == null){
			result.setCode(ResultType.fail.getCode());
			result.setDesc("获取用户信息网络异常，请稍后重试");
			return result;
		}
		
		if(wxRes.getErrcode() != 0){
			result.setCode(ResultType.fail.getCode());
			result.setDesc(wxRes.getErrmsg());
			return result;
		}
		data.setOpenid(wxRes.getOpenid());
		data.setSessionKey(wxRes.getSession_key());
		data.setUnionid(wxRes.getUnionid());
		return result;
	}

	@Override
	public UserInfoRes getUserDetail(Map<String, Object> pojo) {
		return null;
	}

	@Override
	public BaseResponse<WxUnifiedOrderResponse> pay(Map<String, Object> pojo) {
		
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		WxUnifiedOrderRequest payReq = new WxUnifiedOrderRequest();
		payReq.setAppid(PropertyUtil.config.getProperties("appid"));
		payReq.setMch_id(PropertyUtil.config.getProperties("wx_pay_merchant_id"));
		payReq.setBody(pojo.get("eventInfo").toString());
		payReq.setOut_trade_no(pojo.get("orderId").toString());
		payReq.setOpenid(pojo.get("openId").toString());
		payReq.setTrade_type("JSAPI");
		payReq.setNotify_url(pojo.get("notifyUrl").toString());
		payReq.setNonce_str(UUID.randomUUID().toString());
		payReq.setSign(payReq.generateSign());
		
		WxUnifiedOrderRes wxRes = HttpsUtils.get(url, payReq, 5000, WxUnifiedOrderRes.class);
		
		BaseResponse<WxUnifiedOrderResponse> result = new BaseResponse<WxUnifiedOrderResponse>(ResultType.succes);
		WxUnifiedOrderResponse data = new WxUnifiedOrderResponse();
		result.setData(data);
		
		if(wxRes == null ){
			result.setCode(ResultType.fail.getCode());
			result.setDesc("获取用户信息网络异常，请稍后重试");
			return result;
		}
		
		if( !("SUCCESS".equals(wxRes.getResult_code()) &&
				"SUCCESS".equals(wxRes.getReturn_code()))){
			result.setCode(ResultType.fail.getCode());
			result.setDesc(wxRes.getReturn_msg()+";"+wxRes.getErr_code_des());
			return result;
		}
		
		data.setPrepayId(wxRes.getPrepay_id());
		data.setNonceStr(wxRes.getNonce_str());
		data.setAppId(payReq.getAppid());
		data.setTimeStamp(Long.toString(System.currentTimeMillis()));

		return result;
	}
	
}


