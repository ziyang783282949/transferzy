package com.spirit.porker.vo.response;


/**
 * @Description:比赛结束后的冠亚军信息
 * @author: tony.wang
 * @time:2017年7月28日 上午11:55:14
 */

public class RewardPlayer {
	
	/**
	 * 排名
	 */
	private int rank;
	
	/**
	 * 用户昵称
	 */
	private String nickName;
	
	/**
	 * 用户头像
	 */
	private String imgUrl;
	
	/**
	 * 用户卡号
	 *
	 */
	private String cardNos;
	
	/**
	 * 奖励积分
	 *
	 */
	private String point;
	
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getCardNos() {
		return cardNos;
	}

	public void setCardNos(String cardNos) {
		this.cardNos = cardNos;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	
}


