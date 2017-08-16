package com.spirit.porker.enums;

/**
 * 用户赛事报名状态
 * @author spirit-two
 *
 */
public enum PayType {
	
	point(0,"积分"),
	cash(1,"现金")
	;
	
	private int code;
	private String desc;
	
	PayType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static PayType getTypeByCode(int code){
		for(PayType status : PayType.values()){
			if(status.getCode() == code){
				return status;
			}
		}
		return cash;
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
