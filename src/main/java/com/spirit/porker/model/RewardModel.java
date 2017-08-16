package com.spirit.porker.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RewardModel {

	private Integer id;

	/**
	 * 赛事名称
	 */
	private String name;

	/**
	 * 奖励信息
	 */
	private String rewardInfo;

	public Integer getId() {
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

	public String getRewardInfo() {
		return rewardInfo;
	}

	public void setRewardInfo(String rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

}
