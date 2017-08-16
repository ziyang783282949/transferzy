package com.spirit.porker.vo.response;


/**
 * @Description:我的响应结果
 * @author: tony.wang
 * @time:2017年8月1日 下午4:04:32
 */
public class MeResponse {
	
	/**
	 * 会员卡号
	 */
	private String cardNo;
	
	private int score;
	
	private int mttScore;
	
	private int masterScore; 
	
	private int buyIn;
	
	private int buyOut;
	
	private String nickName;
	
	private String imgUrl;
	
	private String realName;
	
	private String phone;
	
	private String certNo;
	
	private String rank;
	
	private String userLevel;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMttScore() {
		return mttScore;
	}

	public void setMttScore(int mttScore) {
		this.mttScore = mttScore;
	}

	public int getMasterScore() {
		return masterScore;
	}

	public void setMasterScore(int masterScore) {
		this.masterScore = masterScore;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getBuyIn() {
		return buyIn;
	}

	public void setBuyIn(int buyIn) {
		this.buyIn = buyIn;
	}

	public int getBuyOut() {
		return buyOut;
	}

	public void setBuyOut(int buyOut) {
		this.buyOut = buyOut;
	}

}


