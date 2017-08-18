package com.spirit.porker.vo.response;

public class RegistResponse {
	private int id;
	private String username;
	private String password;
	private String sex;
	private String urlUserIcon;
	private int credits;
	private int changeCredits;
	private int contributionCredits;
	
	
	
	
	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getChangeCredits() {
		return changeCredits;
	}

	public void setChangeCredits(int changeCredits) {
		this.changeCredits = changeCredits;
	}

	public int getContributionCredits() {
		return contributionCredits;
	}

	public void setContributionCredits(int contributionCredits) {
		this.contributionCredits = contributionCredits;
	}

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
