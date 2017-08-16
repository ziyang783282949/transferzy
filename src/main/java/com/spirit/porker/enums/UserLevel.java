package com.spirit.porker.enums;


public enum UserLevel {
	
	bronze(0,"青铜"),
	sliver(1,"白银"),
	huangjin(1,"黄金"),
	diamond(1,"钻石"),
	royal(1,"皇家"),
	;
	
	private int code;
	private String desc;
	
	UserLevel(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 根据状态码获取类型信息
	 * @param code
	 * @return
	 */
	public static UserLevel getTypeByCode(int code){
		for(UserLevel status : UserLevel.values()){
			if(status.getCode() == code){
				return status;
			}
		}
		return bronze;
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


