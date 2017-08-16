package com.spirit.porker.enums;


public enum OrderTypeState {
	
	userSellTicket(0,"用户卖票"),
	userBuyTicket(1,"用户买票"),
	userBuyCredits(2,"用户买积分"),
	other(3,"其他")
	;
	
	private int code;
	private String desc;
	
	OrderTypeState(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static OrderTypeState getTypeByCode(int code){
		for(OrderTypeState status : OrderTypeState.values()){
			if(status.getCode() == code){
				return status;
			}
		}
		return other;
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


