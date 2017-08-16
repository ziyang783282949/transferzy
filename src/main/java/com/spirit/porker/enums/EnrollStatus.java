package com.spirit.porker.enums;

/**
 * 用户赛事报名状态
 * @author spirit-two
 *
 */
public enum EnrollStatus {
	
	notIn(0,"未报名"),
	in(1,"已报名")
	;
	
	private int code;
	private String desc;
	
	EnrollStatus(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static EnrollStatus getTypeByCode(int code){
		for(EnrollStatus status : EnrollStatus.values()){
			if(status.getCode() == code){
				return status;
			}
		}
		return notIn;
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
