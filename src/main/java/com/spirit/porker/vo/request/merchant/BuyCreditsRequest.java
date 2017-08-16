package com.spirit.porker.vo.request.merchant;

public class BuyCreditsRequest {
	private String userCardno;
	private Integer id;

	public String getUserCardno() {
		return userCardno;
	}

	public void setUserCardno(String userCardno) {
		this.userCardno = userCardno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
