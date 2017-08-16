package com.spirit.porker.vo.request;
/**
 * 每场赛事结束奖励
 *
 */

public class RewardUser {
	/**
	 * 用户排名
	 *
	 */
	private int ranking;
	/**
	 * 用户会员编号
	 *
	 */
	private String cardNo;

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}
