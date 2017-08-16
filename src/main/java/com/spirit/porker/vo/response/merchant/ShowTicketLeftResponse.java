package com.spirit.porker.vo.response.merchant;

import java.math.BigDecimal;

import com.spirit.porker.vo.TicketMisc;

public class ShowTicketLeftResponse {
	private int id;
	private String ticketId;
	private String userCardNo;
	private TicketMisc ticketMisc;
	private Integer state;
	private BigDecimal price;
	public int getId() {
		return id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	

	public TicketMisc getTicketMisc() {
		return ticketMisc;
	}

	public void setTicketMisc(TicketMisc ticketMisc) {
		this.ticketMisc = ticketMisc;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
