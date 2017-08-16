package com.spirit.porker.vo.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 赛事详情类
 * @author: tony.wang
 * @time:2017年7月28日 上午10:00:34
 */
public class EventDetailResponse {

	private Integer id;
	private String statusImgUrl;
	
	private String eventTime;
	
	/**
	 * 赛事年月日
	 */
	private String eventDay;
	/**
	 * 赛事名称
	 */
	private String eventName;
	
	/**
	 * 赛事时分秒
	 */
	private String eventHourMin;
	
	private BigDecimal price;
	
	private String curPot;

	/**
	 * 当前玩家数量
	 */
	private int curPlayersNum;

	/**
	 * 当前玩家详细名称等信息
	 */
	private List<Player> curPlayers;
	
	/**
	 * 盲注级别信息，详细的盲注。
	 */
	private List<Blind> blindInfo;
	
	/**
	 * 奖励信息
	 */
	private List<RewardInfo> rewardInfo;
	
	/**
	 * 最终赛事赢家，冠亚军用户昵称等信息。
	 */
	private List<RewardPlayer> rewardPlayers;
	
	/**
	 * 最少玩家要求
	 */
	private String minPlayers;
	
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
	 * 商家信息
	 */
	private MerchantInfo merchantInfo;
	
	/**
	 * 用户rebuy次数
	 */
	private int rebuyTimes;
	
	/**
	 * 按钮上文字信息：
	 * （1）未报名时，显示立即报名；
	 * （2）已报名后，赛事未开始，展示rebuy按钮，并置灰；
	 * （3）赛事开始后，展示rebuy按钮，超过rebuy次数置之后，rebuy置灰
	 */
	private String buttonText;
	
	/**
	 * 针对预定按钮上可以进行的操作
	 */
	private int operType;
	/**
	 * 购买上限
	 */
	private int rebuyUpperTimes;
	
	public int getRebuyUpperTimes() {
		return rebuyUpperTimes;
	}

	public void setRebuyUpperTimes(int rebuyUpperTimes) {
		this.rebuyUpperTimes = rebuyUpperTimes;
	}

	public MerchantInfo getMerchantInfo() {
		return merchantInfo;
	}

	public void setMerchantInfo(MerchantInfo merchantInfo) {
		this.merchantInfo = merchantInfo;
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

	

	public String getCurPot() {
		return curPot;
	}

	public void setCurPot(String curPot) {
		this.curPot = curPot;
	}

	public int getCurPlayersNum() {
		return curPlayersNum;
	}

	public void setCurPlayersNum(int curPlayersNum) {
		this.curPlayersNum = curPlayersNum;
	}

	

	public List<Player> getCurPlayers() {
		return curPlayers;
	}

	public void setCurPlayers(List<Player> curPlayers) {
		this.curPlayers = curPlayers;
	}

	public List<Blind> getBlindInfo() {
		return blindInfo;
	}

	public void setBlindInfo(List<Blind> blindInfo) {
		this.blindInfo = blindInfo;
	}

	public List<RewardInfo> getRewardInfo() {
		return rewardInfo;
	}

	public void setRewardInfo(List<RewardInfo> rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

	public List<RewardPlayer> getRewardPlayers() {
		return rewardPlayers;
	}

	public void setRewardPlayers(List<RewardPlayer> rewardPlayers) {
		this.rewardPlayers = rewardPlayers;
	}

	public String getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(String minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
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

	public String getEventStatusName() {
		return eventStatusName;
	}

	public void setEventStatusName(String eventStatusName) {
		this.eventStatusName = eventStatusName;
	}

	public int getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(int enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public String getEnrollStatusName() {
		return enrollStatusName;
	}

	public void setEnrollStatusName(String enrollStatusName) {
		this.enrollStatusName = enrollStatusName;
	}

	public int getRebuyTimes() {
		return rebuyTimes;
	}

	public void setRebuyTimes(int rebuyTimes) {
		this.rebuyTimes = rebuyTimes;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public int getOperType() {
		return operType;
	}

	public void setOperType(int operType) {
		this.operType = operType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStatusImgUrl() {
		return statusImgUrl;
	}

	public void setStatusImgUrl(String statusImgUrl) {
		this.statusImgUrl = statusImgUrl;
	}

}
