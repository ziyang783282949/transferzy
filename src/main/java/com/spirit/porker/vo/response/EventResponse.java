package com.spirit.porker.vo.response;

import java.math.BigDecimal;

public class EventResponse {
	
	/**
	 * 赛事ID
	 */
	private Integer id;
	
	/**
	 * 赛事名称
	 */
	private String name;
	
	/**
	 * 赛事时间yyyy-mm-dd hh:mm:ss
	 */
	private String eventTime;
	
	/**
	 * 赛事年月日
	 */
	private String eventDay;
	
	/**
	 * 赛事时分秒
	 */
	private String eventHourMin;
	
	/**
	 * 当前总奖池
	 */
	private BigDecimal curPot;
	
	/**
	 * 当前报名用户数量
	 */
	private int curPlayersNum;
	
	/**
	 * 当前报名用户信息，包括用户昵称等信息
	 */
	private String curPlayers;
	
	/**
	 * 赛事状态见枚举类{@link com.spirit.porker.enums.EventStatus}
	 */
	private int eventStatus;
	
	/**
	 * 赛事状态见枚举类名称，方便前端展示，和字段eventStatus保持
	 */
	private String eventStatusName;
	
	/**
	 * 用户报名状态（前端需要针对每个用户请求后展示报名状态）
	 * {@link com.spirit.porker.enums.EnrollStatus}
	 */
	private int enrollStatus;
	
	/**
	 * 用户报名状态，方便前端展示，和字段enrollStatus保持
	 */
	private String enrollStatusName;
	
	/**
	 * 赛事截止报名时间
	 */
	private String eventCloseTime;
	
	/**
	 * 是否可报名 0 可以 -1不可以
	 */
	private int canBook;
	
	/**
	 * 报名费用
	 */
	private BigDecimal price;
	
	private String players;
	
	/**
	 * 赛事类型图片
	 */
	private String imgUrl;
	
	/**
	 * 赛事状态图片
	 */
	private String statusImgUrl;

	public String getStatusImgUrl() {
		return statusImgUrl;
	}

	public void setStatusImgUrl(String statusImgUrl) {
		this.statusImgUrl = statusImgUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public int getCurPlayersNum() {
		return curPlayersNum;
	}

	public void setCurPlayersNum(int curPlayersNum) {
		this.curPlayersNum = curPlayersNum;
	}

	public String getCurPlayers() {
		return curPlayers;
	}

	public void setCurPlayers(String curPlayers) {
		this.curPlayers = curPlayers;
	}

	public BigDecimal getCurPot() {
		return curPot;
	}

	public void setCurPot(BigDecimal curPot) {
		this.curPot = curPot;
	}

	public String getEventDay() {
		return eventDay;
	}

	public void setEventDay(String eventDay) {
		this.eventDay = eventDay;
	}

	public String getEventHourMin() {
		return eventHourMin;
	}

	public void setEventHourMin(String eventHourMin) {
		this.eventHourMin = eventHourMin;
	}

	public int getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
	}

	public int getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(int enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public String getEventStatusName() {
		return eventStatusName;
	}

	public void setEventStatusName(String eventStatusName) {
		this.eventStatusName = eventStatusName;
	}

	public String getEnrollStatusName() {
		return enrollStatusName;
	}

	public void setEnrollStatusName(String enrollStatusName) {
		this.enrollStatusName = enrollStatusName;
	}

	public String getEventCloseTime() {
		return eventCloseTime;
	}

	public void setEventCloseTime(String eventCloseTime) {
		this.eventCloseTime = eventCloseTime;
	}

	public int getCanBook() {
		return canBook;
	}

	public void setCanBook(int canBook) {
		this.canBook = canBook;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
