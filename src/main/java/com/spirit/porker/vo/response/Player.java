package com.spirit.porker.vo.response;

public class Player {
	
	/**
	 * 用户Id信息
	 */
	private Integer id;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 头像地址
	 */
	private String imgUrl;
	
	/**
	 * 身份证号
	 */
	private String certNo;
	
	/**
	 * 真是姓名
	 */
	private String realName;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 本应用从微信开放平台上获取到的用户在本应用的唯一标识（一个用户只会有一个，openId同一个应用内不会重复）
	 */
	private String openId;
	
	private String cardNo;
	
	private int rebuyTimes;
	
	/**
	 * 是否参赛
	 */
	private int enrollStatus;
	
	/**
	 * 当前用户的哪个订单上绑定的当前赛事。
	 */
	private String orderId;

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

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
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

	public Integer getId() {
		return id;
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

	public int getRebuyTimes() {
		return rebuyTimes;
	}

	public void setRebuyTimes(int rebuyTimes) {
		this.rebuyTimes = rebuyTimes;
	}

	public int getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(int enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
