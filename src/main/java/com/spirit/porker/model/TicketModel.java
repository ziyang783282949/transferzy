package com.spirit.porker.model;

import java.math.BigDecimal;

public class TicketModel {
	
	private int id;
	private String ticketId;
	private String userCardNo;
	private String ticketMisc;
	private Integer state;
	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
