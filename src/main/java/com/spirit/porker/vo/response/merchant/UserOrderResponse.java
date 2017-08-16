package com.spirit.porker.vo.response.merchant;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserOrderResponse {
	/**
	 * 订单ID，自定义生成方式
	 */
	private String id;
	/**
	 * 用户会员信息
	 */
	private String cardNo;
	
	/**
	 * 订单总金额
	 */
	private BigDecimal orderAmount;
	
	/**
	 * 支付金额
	 */
	private BigDecimal payAmount;
	
	/**
	 * 支付方式{@link com.spirit.porker.enums.PayType}
	 */
	private int payType;
	
	/**
	 * 订单生成事件
	 */
	private Timestamp orderTime;
	
	/**
	 * 支付时间
	 */
	private Timestamp paySuccessTime;
	
	/**
	 * @{link com.spirit.porker.enums.OrderStatus}
	 */
	private int orderStatus;
	
	/**
	 * 退款金额
	 */
	private BigDecimal refundAmount;
	
	/**
	 * 赛事ID
	 */
	private int eventId;
	/**
	 * 赛事名字
	 */
	private String eventName;
	/**
	 * 赛事时间
	 */
	private String eventTime;
	
	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Timestamp getPaySuccessTime() {
		return paySuccessTime;
	}

	public void setPaySuccessTime(Timestamp paySuccessTime) {
		this.paySuccessTime = paySuccessTime;
	}
}
