package com.spirit.porker.vo.request;

import java.sql.Timestamp;

public class AddBlindListRequest extends BaseRequest{
	
	private String name;
	private String blindList;
	private Timestamp createTime;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBlindList() {
		return blindList;
	}

	public void setBlindList(String blindList) {
		this.blindList = blindList;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
