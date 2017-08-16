package com.spirit.porker.vo.request.merchant;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.spirit.porker.enums.EventType;

/**
 * @author DELL
 *
 */
public class AddEventRequest {

	/**
	 * 赛事名称
	 */
	private String name;

	/**
	 * 赛事开始时间
	 */
	private Timestamp eventTime;

	/**
	 * 报名费用，比如实际展示给用户的时550元，其中500元是price，50元时serviceFee，总奖池和price有关，和serviceFee无关。
	 */
	private BigDecimal price;

	/**
	 * 服务费
	 */
	private BigDecimal serviceFee;

	/**
	 * 奖池技术
	 */
	private BigDecimal basePot;

	/**
	 * 最少人数要求
	 */
	private Integer minPlayers;

	/**
	 * 最少人数要求
	 */
	private Integer maxPlayers;

	/**
	 * 盲注类型，从盲注表获取
	 */
	private Integer blindType;

	/**
	 * 从奖励表获取
	 */
	private Integer rewardType;

	private String enrollCloseTime;
	/**
	 * 赛事类型（周赛，日赛）{@link com.spirit.porker.enums.EventType}
	 */
	private Integer eventType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getEventTime() {
		return eventTime;
	}

	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public BigDecimal getBasePot() {
		return basePot;
	}

	public void setBasePot(BigDecimal basePot) {
		this.basePot = basePot;
	}

	public Integer getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(Integer minPlayers) {
		this.minPlayers = minPlayers;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Integer getBlindType() {
		return blindType;
	}

	public void setBlindType(Integer blindType) {
		this.blindType = blindType;
	}

	public Integer getRewardType() {
		return rewardType;
	}

	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}

	public String getEnrollCloseTime() {
		return enrollCloseTime;
	}

	public void setEnrollCloseTime(String enrollCloseTime) {
		this.enrollCloseTime = enrollCloseTime;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

}
