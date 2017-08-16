package com.spirit.porker.vo.response;

public class MerchantInfo {
	
	private String tel;
	
	/**
	 * 经度
	 */
	private String longitude;
	
	/**
	 * 维度
	 */
	private String latitude;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
