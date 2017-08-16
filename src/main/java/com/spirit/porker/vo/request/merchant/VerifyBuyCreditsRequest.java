package com.spirit.porker.vo.request.merchant;

public class VerifyBuyCreditsRequest {
	private String id;
	private String verifyMessage;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVerifyMessage() {
		return verifyMessage;
	}

	public void setVerifyMessage(String verifyMessage) {
		this.verifyMessage = verifyMessage;
	}

}
