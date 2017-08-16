package com.spirit.porker.agent.wechat.request;

import com.spirit.porker.util.MD5Util;

/**
 * @Description:TODO
 * @author: tony.wang
 * @time:2017年8月2日 下午3:13:28
 */
public class WxUnifiedOrderRequest extends BaseRequest{
	
	/**
	 * 微信分配的小程序ID
	 */
	private String appid;
	
	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;
	
	/**
	 * 随机字符串，不长于32位。推荐
	 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
	 */
	private String nonce_str;
	
	/**
	 * 签名，详见签名生成算法
	 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
	 */
	private String sign;
	
	/**
	 * 商品简单描述，该字段须严格按照规范传递，具体请见
	 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
	 */
	private String body;
	
	/**
	 * 单品优惠字段(微信暂未上线，不用考虑)
	 */
	private String detail;
	
	/**
	 * 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见
	 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
	 */
	private String out_trade_no;
	
	/**
	 * 订单总金额，单位为分，详见
	 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
	 */
	private int total_fee;
	
	/**
	 * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	 */
	private String spbill_create_ip;
	
	/**
	 * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
	 */
	private String notify_url;
	
	/**
	 * 小程序取值如下：JSAPI，详细说明见参数规定
	 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
	 */
	private String trade_type = "JSAPI";
	
	/**
	 * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考
	 * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161122
	 */
	private String openid;
	
	public String generateSign(){
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("appid="+appid);
		sBuilder.append("&body="+body);
		sBuilder.append("&mch_id="+mch_id);
		sBuilder.append("&nonce_str="+nonce_str);
		sBuilder.append("&notify_url="+notify_url);
		sBuilder.append("&out_trade_no="+out_trade_no);
		sBuilder.append("&openid="+openid);
		sBuilder.append("&spbill_create_ip="+spbill_create_ip);
		sBuilder.append("&trade_type="+trade_type);
		sBuilder.append("&total_fee="+total_fee);
		
		return MD5Util.getMD5(sBuilder.toString()).toUpperCase();
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}


