package com.spirit.porker.vo.response;

import java.math.BigDecimal;

public class OrderListResponse {
	
	private String orderId;
	
	/**
	 * 下单时间
	 */
	private String orderTime;
	
	/**
	 * 赛事开始时间
	 */
	private String eventBeginTime;
	
	/**
	 * 报名费 
	 */
	private BigDecimal price;
	
	private String eventName;
	
	private int eventId;
	
	/**
	 * 10/50 (最低14人)
	 */
	private String players;
	
	/**
	 * 是否可支付 1 可以 0不可以 默认0
	 */
	private int canPay;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getEventBeginTime() {
		return eventBeginTime;
	}

	public void setEventBeginTime(String eventBeginTime) {
		this.eventBeginTime = eventBeginTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getCanPay() {
		return canPay;
	}

	public void setCanPay(int canPay) {
		this.canPay = canPay;
	}
	
}


