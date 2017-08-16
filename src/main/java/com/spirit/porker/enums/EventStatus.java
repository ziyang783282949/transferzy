package com.spirit.porker.enums;

/**
 * 用户赛事报名状态
 * @author spirit-two
 *
 */
public enum EventStatus {
	
	init(0,"未开始"),
	doing(1,"已开始"),
	done(2,"已结束"),
	canceled(3,"已取消")
	;
	
	private int code;
	private String desc;
	
	EventStatus(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static EventStatus getTypeByCode(int code){
		for(EventStatus status : EventStatus.values()){
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
