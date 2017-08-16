package com.spirit.porker.model;

import java.sql.Timestamp;

public class BlindModel {
	private Integer id;
	private String name;
	private String blindList;
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
