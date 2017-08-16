package com.spirit.porker.vo.request;

/**
 * 每场赛事结束奖励用户list(包含赛事场次id)
 *
 */
public class RewardUserListRequest {
	
	private String eventId;
	
	private String userList;
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	
}
