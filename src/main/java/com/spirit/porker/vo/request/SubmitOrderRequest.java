package com.spirit.porker.vo.request;

import com.spirit.porker.vo.response.PojoValidatorErrors;

public class SubmitOrderRequest extends BaseRequest{
	
	/**
	 * 赛事ID信息
	 */
	private Integer eventId;
	
	/**
	 * 支付方式，见{@link com.spirit.porker.enums.PayType}
	 */
	private Integer payType;
	
	private String cardNo;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}
