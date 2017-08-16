package com.spirit.porker.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description:报名订单信息
 * @author: tony.wang
 * @time:2017年7月28日 下午7:16:33
 */
public class OrderModel {
	
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
	 * 只记录下单时支付方式，后续rebuy时支付方式不记录。
	 */
	private Integer payType;
	
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
	private Integer orderStatus;
	
	/**
	 * 退款金额
	 */
	private BigDecimal refundAmount;
	
	/**
	 * 赛事ID
	 */
	private int eventId;
	
	private BigDecimal rebuyPrice;
	
	private Integer rebuyTimes;
	/**
	 * 赛事基本信息，見 {@link com.spirit.porker.vo.EventMisc}
	 */
	private String eventMisc;
	private String scoreMisc;
	/**
	 * 订单类型，見 {@link com.spirit.porker.enums.OrderTypeState}
	 */
	private Integer type;
	/**
	 * 订单类型说明，見 {@link com.spirit.porker.enums.OrderTypeState}
	 */
	private String typeMisc;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeMisc() {
		return typeMisc;
	}

	public void setTypeMisc(String typeMisc) {
		this.typeMisc = typeMisc;
	}

	public String getScoreMisc() {
		return scoreMisc;
	}

	public void setScoreMisc(String scoreMisc) {
		this.scoreMisc = scoreMisc;
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
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
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
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
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
	public BigDecimal getRebuyPrice() {
		return rebuyPrice;
	}
	public void setRebuyPrice(BigDecimal rebuyPrice) {
		this.rebuyPrice = rebuyPrice;
	}
	public Integer getRebuyTimes() {
		return rebuyTimes;
	}
	public void setRebuyTimes(Integer rebuyTimes) {
		this.rebuyTimes = rebuyTimes;
	}
	public String getEventMisc() {
		return eventMisc;
	}
	public void setEventMisc(String eventMisc) {
		this.eventMisc = eventMisc;
	}

}


