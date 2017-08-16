package com.spirit.porker.enums;


public enum TicketState {
	
	normal(0,"未使用"),
	check(1,"待审核"),
	delete(2,"已出售"),
	lock(3,"锁定"),
	unlock(4,"解锁")
	;
	
	private int code;
	private String desc;
	
	TicketState(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static TicketState getTypeByCode(int code){
		for(TicketState status : TicketState.values()){
			if(status.getCode() == code){
				return status;
			}
		}
		return normal;
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


