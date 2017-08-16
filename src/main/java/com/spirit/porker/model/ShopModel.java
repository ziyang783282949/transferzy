package com.spirit.porker.model;

import java.math.BigDecimal;

public class ShopModel {
	private int id;
	private String ticketId;
	private String userCardNo;
	private String ticketMisc;
	private Integer state;
	private BigDecimal normalPrice;
	private BigDecimal userSellPrice;
	private BigDecimal userBuyPrice;
	
	public BigDecimal getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(BigDecimal normalPrice) {
		this.normalPrice = normalPrice;
	}

	public BigDecimal getUserSellPrice() {
		return userSellPrice;
	}

	public void setUserSellPrice(BigDecimal userSellPrice) {
		this.userSellPrice = userSellPrice;
	}

	public BigDecimal getUserBuyPrice() {
		return userBuyPrice;
	}

	public void setUserBuyPrice(BigDecimal userBuyPrice) {
		this.userBuyPrice = userBuyPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getUserCardNo() {
		return userCardNo;
	}

	public void setUserCardNo(String userCardNo) {
		this.userCardNo = userCardNo;
	}

	public String getTicketMisc() {
		return ticketMisc;
	}

	public void setTicketMisc(String ticketMisc) {
		this.ticketMisc = ticketMisc;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
