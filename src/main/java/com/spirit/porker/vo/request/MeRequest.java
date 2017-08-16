package com.spirit.porker.vo.request;

import com.spirit.porker.vo.response.PojoValidatorErrors;

public class MeRequest extends BaseRequest{
	
	/**
	 * 身份证号
	 */
	private String certNo;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	private String cardNo;

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}


