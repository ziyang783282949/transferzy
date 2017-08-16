package com.spirit.porker.vo.response.merchant;

import java.math.BigDecimal;
import java.util.List;

import com.spirit.porker.model.EventModel;
import com.spirit.porker.model.OrderModel;

public class EventQueryResponse {
	
	private List<EventModel> eventModelList;
	
	private BigDecimal totalPot;
	
	private int totalPlayers;
	
	private int validEventNumbers;
	
	private int totalEvent;
	
	private BigDecimal validPot;
	

	public int getValidEventNumbers() {
		return validEventNumbers;
	}

	public void setValidEventNumbers(int validEventNumbers) {
		this.validEventNumbers = validEventNumbers;
	}

	public int getTotalEvent() {
		return totalEvent;
	}

	public void setTotalEvent(int totalEvent) {
		this.totalEvent = totalEvent;
	}

	public BigDecimal getValidPot() {
		return validPot;
	}

	public void setValidPot(BigDecimal validPot) {
		this.validPot = validPot;
	}

	public List<EventModel> getEventModelList() {
		return eventModelList;
	}

	public void setEventModelList(List<EventModel> eventModelList) {
		this.eventModelList = eventModelList;
	}

	public BigDecimal getTotalPot() {
		return totalPot;
	}

	public void setTotalPot(BigDecimal totalPot) {
		this.totalPot = totalPot;
	}

	public int getTotalPlayers() {
		return totalPlayers;
	}

	public void setTotalPlayers(int totalPlayers) {
		this.totalPlayers = totalPlayers;
	}

	

	
}
