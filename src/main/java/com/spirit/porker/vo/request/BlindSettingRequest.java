package com.spirit.porker.vo.request;

public class BlindSettingRequest {

	/**
	 * 编号
	 */
	private int id;
	
	/**
	 * 大盲
	 */
	private int bigBlind;
	
	/**
	 * 小盲
	 */
	private int smallBlind;
	
	/**
	 * 前注
	 */
	private int note;
	
	/**
	 * 涨盲时间（单位：分钟）
	 */
	private int time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBigBlind() {
		return bigBlind;
	}

	public void setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
	}

	public int getSmallBlind() {
		return smallBlind;
	}

	public void setSmallBlind(int smallBlind) {
		this.smallBlind = smallBlind;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
