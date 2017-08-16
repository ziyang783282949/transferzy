package com.spirit.porker.vo.request.merchant;

public class UserSellTicketRequest {
	private String userCardno;
	private String ticketId;

	public String getUserCardno() {
		return userCardno;
	}

	public void setUserCardno(String userCardno) {
		this.userCardno = userCardno;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

}
