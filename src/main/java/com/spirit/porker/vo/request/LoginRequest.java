package com.spirit.porker.vo.request;

import com.spirit.porker.annotation.NotEmpty;

public class LoginRequest extends BaseRequest{
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
