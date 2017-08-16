
package com.spirit.porker.vo.request;

public class PayRequest extends BaseRequest{
	
	/**
	 * 赛事ID信息
	 */
	private String orderId;
	
	private Integer payType;

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
