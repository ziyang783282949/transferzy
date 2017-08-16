package com.spirit.porker.vo.response;


public class OrderDetailResponse {
	
	private EventDetailResponse eventDetail;
	
	/**
	 * 见枚举类{@link com.spirit.porker.enums.OrderStatus}
	 */
	private int orderStatus;

	public EventDetailResponse getEventDetail() {
		return eventDetail;
	}

	public void setEventDetail(EventDetailResponse eventDetail) {
		this.eventDetail = eventDetail;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	} 

}


