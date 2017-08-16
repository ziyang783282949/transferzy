package com.spirit.porker.vo.response;

import java.sql.Timestamp;

public class UserModelListResponse {
	private int totalNums;
	private int totalBuyIn;
	private int totalBuyOut;

	public int getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(int totalNums) {
		this.totalNums = totalNums;
	}

	public int getTotalBuyIn() {
		return totalBuyIn;
	}

	public void setTotalBuyIn(int totalBuyIn) {
		this.totalBuyIn = totalBuyIn;
	}

	public int getTotalBuyOut() {
		return totalBuyOut;
	}

	public void setTotalBuyOut(int totalBuyOut) {
		this.totalBuyOut = totalBuyOut;
	}

}
