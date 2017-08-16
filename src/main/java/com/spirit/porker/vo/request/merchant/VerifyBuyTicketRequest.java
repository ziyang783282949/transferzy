package com.spirit.porker.vo.request.merchant;

public class VerifyBuyTicketRequest {
	private String orderId;
	private String verifyMessage;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getVerifyMessage() {
		return verifyMessage;
	}

	public void setVerifyMessage(String verifyMessage) {
		this.verifyMessage = verifyMessage;
	}

}
