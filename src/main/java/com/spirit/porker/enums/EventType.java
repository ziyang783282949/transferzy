package com.spirit.porker.enums;

/**
 * 赛事类型
 * @author spirit-two
 *
 */
public enum EventType {
	
	week(0,"周赛"),
	day(1,"日赛"),
	sichou(2,"丝绸之路杯"),
	other(3,"其他")
	;
	
	private int code;
	private String desc;
	
	EventType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static EventType getTypeByCode(int code){
		for(EventType status : EventType.values()){
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
