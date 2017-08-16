package com.spirit.porker.vo.response;


/**
 * @Description:每场赛事中赛事名次奖励以及其他奖励信息
 * @author: tony.wang
 * @time:2017年7月28日 上午11:51:09
 */
public class RewardInfo {
	
	/**
	 * 编号
	 */
	private Integer id;
	
	/**
	 * 奖励积分值
	 */
	private int point;
	
	/**
	 * 其他奖励信息
	 */
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}


