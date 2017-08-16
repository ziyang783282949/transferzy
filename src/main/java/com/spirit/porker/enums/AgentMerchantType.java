package com.spirit.porker.enums;


public enum AgentMerchantType {
	
	wechat(0,"微信"),
	ali(1,"支付宝")
	;
	
	private int code;
	private String desc;
	
	AgentMerchantType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static AgentMerchantType getTypeByCode(int code){
		for(AgentMerchantType type : AgentMerchantType.values()){
			if(type.getCode() == code){
				return type;
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


