package com.spirit.porker.enums;

/**
 * 用户赛事报名状态
 * @author spirit-two
 *
 */
public enum OrderStatus {
	
	init(0,"待支付"),
	payed(1,"已支付"),
	joined(2,"已参赛"),
	canceled(3,"已取消")
	;
	
	private int code;
	private String desc;
	
	OrderStatus(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static OrderStatus getTypeByCode(int code){
		for(OrderStatus status : OrderStatus.values()){
			if(status.getCode() == code){
				return status;
			}
		}
		return init;
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
