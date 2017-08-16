package com.spirit.porker.vo.response;


/**
 * @Description:微信支付结果回调后，给微信的返回结果
 * @author: tony.wang
 * @time:2017年8月3日 下午2:25:40
 */
public class WxPayCallBackResponse {
	
	public static final String success = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	
	public static final String signFail = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名错误]]></return_msg></xml>";
	
	public static final String paramFail = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[参数错误]]></return_msg></xml>";
	
	public static final String orderNotExist = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[不存在该订单]]></return_msg></xml>";
	
	public static final String sysFail = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[系统错误]]></return_msg></xml>";
	
	private String return_code;
	
	private String return_msg;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
}


