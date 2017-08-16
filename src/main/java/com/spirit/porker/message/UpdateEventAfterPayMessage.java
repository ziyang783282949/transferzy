package com.spirit.porker.message;

public class UpdateEventAfterPayMessage {
	
	/**
	 * 赛事ID
	 */
	private Integer eventId;
	
	/**
	 * 用户会员卡号
	 */
	private String cardNo;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 详见{@link com.spirit.porker.enums.PayType}
	 */
	private int payType;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}
	
}


