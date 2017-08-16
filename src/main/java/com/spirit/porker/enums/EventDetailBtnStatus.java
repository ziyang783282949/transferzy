package com.spirit.porker.enums;

/**
 * 用户赛事报名状态
 * @author spirit-two
 *
 */
public enum EventDetailBtnStatus {
	
	toEnroll(0,"立即报名"),
	hidden(1,"隐藏按钮"),
	greyRebuy(2,"置灰rebuy"),
	toRebuy(3,"rebuy"),
	
	;
	
	private int code;
	private String desc;
	
	EventDetailBtnStatus(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static EventDetailBtnStatus getTypeByCode(int code){
		for(EventDetailBtnStatus status : EventDetailBtnStatus.values()){
			if(status.getCode() == code){
				return status;
			}
		}
		return toEnroll;
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
