package com.spirit.porker.message;


public class UpdateOrderStatusMessage {
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 事件名：比如支付成功，入店成功
	 */
	private String event;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
}


