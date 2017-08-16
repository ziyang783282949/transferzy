package com.spirit.porker.vo.response.merchant;

import java.util.List;

public class UserOrderListResponse {
	
	private UserBasicInfo UserBasicInfo;
	
	private List<UserOrderResponse> userOrderList;

	public UserBasicInfo getUserBasicInfo() {
		return UserBasicInfo;
	}

	public void setUserBasicInfo(UserBasicInfo userBasicInfo) {
		UserBasicInfo = userBasicInfo;
	}

	public List<UserOrderResponse> getUserOrderList() {
		return userOrderList;
	}

	public void setUserOrderList(List<UserOrderResponse> userOrderList) {
		this.userOrderList = userOrderList;
	}

}
