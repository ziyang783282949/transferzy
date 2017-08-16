package com.spirit.porker.vo.response;

import java.util.List;

public class BlindResponse {

	private String name;
	private List<Blind> blindList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Blind> getBlindList() {
		return blindList;
	}

	public void setBlindList(List<Blind> blindList) {
		this.blindList = blindList;
	}

}
