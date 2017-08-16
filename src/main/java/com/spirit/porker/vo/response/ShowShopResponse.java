package com.spirit.porker.vo.response;

import java.util.List;

import com.spirit.porker.model.ShopModel;

public class ShowShopResponse {
	private List<Exchange> exchanges;
	private List<TicketResponse> tickets;

	public List<Exchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}

	public List<TicketResponse> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketResponse> tickets) {
		this.tickets = tickets;
	}

	

}
