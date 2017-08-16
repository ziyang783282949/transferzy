package com.spirit.porker.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class EventModel {
	
	private Integer id;
	
	/**
	 * 赛事名称
	 */
	private String name;
	
	/**
	 * 赛事开始时间
	 */
	private Timestamp eventTime;
	
	/**
	 * 赛事状态
	 */
	private int eventStatus;
	
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
	private int minPlayers;
	
	/**
	 * 最少人数要求
	 */
	private int maxPlayers;
	
	/**
	 * 盲注类型，从盲注表获取 
	 */
	private String blindInfo;
	
	/**
	 * 从奖励表获取
	 */
	private int rewardType;
	
	/**
	 * 用户所有的rebuy次数总和。
	 */
	private int rebuyTimes;
	
	/**
	 * 当前报名用户信息（包括用户的id和昵称等）
	 */
	private String curPlayers;
	
	private Timestamp enrollCloseTime;
	
	/**
	 * json形式，被奖励的用户
	 */
	private String rewardPlayers;
	
	/**
	 * 奖励信息
	 */
	private String rewardInfo;
	
	/**
	 * 比赛结束时间，一般由客服来确认关闭赛事，记录操作时的时间。
	 */
	private Timestamp eventEndTime;
	
	/**
	 * 比赛创建时间
	 */
	private Timestamp createTime;
	
	private Timestamp beginTime;
	
	private Timestamp endTime;
	
	/**
	 * 赛事类型（周赛，日赛）{@link com.spirit.porker.enums.EventType}
	 */
	private int eventType;
	
	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public Integer getId(){ 
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public int getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getBasePot() {
		return basePot;
	}

	public void setBasePot(BigDecimal basePot) {
		this.basePot = basePot;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public String getBlindInfo() {
		return blindInfo;
	}

	public void setBlindInfo(String blindInfo) {
		this.blindInfo = blindInfo;
	}

	public int getRewardType() {
		return rewardType;
	}

	public void setRewardType(int rewardType) {
		this.rewardType = rewardType;
	}

	public int getRebuyTimes() {
		return rebuyTimes;
	}

	public void setRebuyTimes(int rebuyTimes) {
		this.rebuyTimes = rebuyTimes;
	}

	public Timestamp getEnrollCloseTime() {
		return enrollCloseTime;
	}

	public void setEnrollCloseTime(Timestamp enrollCloseTime) {
		this.enrollCloseTime = enrollCloseTime;
	}

	public String getRewardPlayers() {
		return rewardPlayers;
	}

	public void setRewardPlayers(String rewardPlayers) {
		this.rewardPlayers = rewardPlayers;
	}

	public String getRewardInfo() {
		return rewardInfo;
	}

	public void setRewardInfo(String rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

	public Timestamp getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(Timestamp eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public String getCurPlayers() {
		return curPlayers;
	}

	public void setCurPlayers(String curPlayers) {
		this.curPlayers = curPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
}
