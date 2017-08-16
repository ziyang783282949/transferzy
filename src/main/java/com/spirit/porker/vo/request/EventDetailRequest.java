package com.spirit.porker.vo.request;

import com.spirit.porker.vo.response.PojoValidatorErrors;

public class EventDetailRequest extends BaseRequest{
	
	/**
	 * 赛事ID信息
	 */
	private Integer eventId;
	
	private String cardNo;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Override
	public PojoValidatorErrors validate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}
