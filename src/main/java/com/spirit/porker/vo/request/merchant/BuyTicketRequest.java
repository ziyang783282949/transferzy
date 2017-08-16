package com.spirit.porker.vo.request.merchant;

public class BuyTicketRequest {
	private String UserCardNo;
	private String TicketId;

	public String getUserCardNo() {
		return UserCardNo;
	}

	public void setUserCardNo(String userCardNo) {
		UserCardNo = userCardNo;
	}

	public String getTicketId() {
		return TicketId;
	}

	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}

}
