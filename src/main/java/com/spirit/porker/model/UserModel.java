package com.spirit.porker.model;

import java.sql.Timestamp;

public class UserModel {
	
	private Integer id;
	
	private String openId;
	
	private String cardNo;
	
	
	/**
	 * mtt赛事总积分，此积分是每次比赛奖励的用户积分总额
	 * 比如 第一名8000积分，实际未日月潭门票+3000积分。
	 * mttScore只记录8000积分。
	 */
	private Integer curMttScore;
	private Integer lastMttScore;
	/**
	 * mtt赛事总积分，此积分是每次比赛奖励的用户积分总额
	 * 比如 第一名8000积分，实际未日月潭门票+3000积分。
	 * masterScore记录的是名次信息，比如ITM总共20个人，
	 * 那么第一名分数为20-0 = 20
	 * 第二名分数为20-1 = 19
	 * 最后一名为20-19=1
	 * 以此类推
	 */
	private Integer curMasterScore;
	private Integer lastMasterScore;
	
	/**
	 * 真实用户积分值。
	 * mtt赛事总积分，此积分是每次比赛奖励的用户积分总额
	 * 比如 第一名8000积分，实际未日月潭门票+3000积分。
	 * mttScore只记录8000积分。
	 * score记录3000积分，可以用于用户报名。
	 * 
	 */
	private Integer masterChange;
	private Integer mttChange;
	private Integer score;
	
	/**
	 * 买入总额
	 */
	private Integer buyIn;
	
	/**
	 * 买出总额
	 */
	private Integer buyOut;


	/**
	 * 图像地址
	 */
	private String nickName;

	/**
	 * 图像地址
	 */
	private String imgUrl;
	
	/**
	 *  0 男 1女
	 */
	private Integer sex;
	
	
	/**
	 * 图像所在城市
	 */
	private String city;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 身份证号
	 */
	private String certNo;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 用户级别
	 */
	private int userLevel;
	
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public Integer getCurMttScore() {
		return curMttScore;
	}

	public void setCurMttScore(Integer curMttScore) {
		this.curMttScore = curMttScore;
	}

	public Integer getLastMttScore() {
		return lastMttScore;
	}

	public void setLastMttScore(Integer lastMttScore) {
		this.lastMttScore = lastMttScore;
	}

	public Integer getCurMasterScore() {
		return curMasterScore;
	}

	public void setCurMasterScore(Integer curMasterScore) {
		this.curMasterScore = curMasterScore;
	}

	public Integer getLastMasterScore() {
		return lastMasterScore;
	}

	public void setLastMasterScore(Integer lastMasterScore) {
		this.lastMasterScore = lastMasterScore;
	}

	public Integer getMasterChange() {
		return masterChange;
	}

	public void setMasterChange(Integer masterChange) {
		this.masterChange = masterChange;
	}

	public Integer getMttChange() {
		return mttChange;
	}

	public void setMttChange(Integer mttChange) {
		this.mttChange = mttChange;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getBuyIn() {
		return buyIn;
	}

	public void setBuyIn(Integer buyIn) {
		this.buyIn = buyIn;
	}

	public Integer getBuyOut() {
		return buyOut;
	}

	public void setBuyOut(Integer buyOut) {
		this.buyOut = buyOut;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}

