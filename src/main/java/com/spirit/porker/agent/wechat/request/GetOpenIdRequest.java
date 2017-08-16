package com.spirit.porker.agent.wechat.request;

/**
 * @Description:获取用户在微信平台的小程序上本应用唯一ID
 * @author: tony.wang
 * @time:2017年8月2日 上午10:17:27
 */
public class GetOpenIdRequest extends BaseRequest{
	
	/**
	 * 小程序唯一标识，申请后为固定值
	 */
	private String appid;
	
	/**
	 * 小程序的 app secret
	 */
	private String secret;
	
	/**
	 * 前端调用wx.login登录后获取的code
	 */
	private String js_code;
	
	private String grant_type = "authorization_code";

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getJs_code() {
		return js_code;
	}

	public void setJs_code(String js_code) {
		this.js_code = js_code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
}


