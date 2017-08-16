package com.spirit.porker.enums;

/**
 * 用户赛事报名状态
 * @author spirit-two
 *
 */
public enum OpenIdType {
	
	wechat(0,"微信小程序"),
	aliPay(1,"支付宝小程序")
	;
	
	private int code;
	private String desc;
	
	OpenIdType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static OpenIdType getTypeByCode(int code){
		for(OpenIdType status : OpenIdType.values()){
			if(status.getCode() == code){
				return status;
			}
		}
		return wechat;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
