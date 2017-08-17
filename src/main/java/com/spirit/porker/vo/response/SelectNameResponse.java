package com.spirit.porker.vo.response;

public class SelectNameResponse {
	private int id;
	private String username;
	private String password;
	private String sex;
	private String urlUserIcon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUrlUserIcon() {
		return urlUserIcon;
	}

	public void setUrlUserIcon(String urlUserIcon) {
		this.urlUserIcon = urlUserIcon;
	}

}
