package com.spirit.porker.vo.request;

public class OrderListRequest extends BaseRequest{
	
	/**
	 * 订单类型，见枚举类{@link com.spirit.porker.enums.EventStatus}
	 */
	private int type;
	
	private String cardNo;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}


