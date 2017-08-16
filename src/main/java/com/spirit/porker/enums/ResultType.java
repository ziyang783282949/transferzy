package com.spirit.porker.enums;

public enum ResultType {
	
	succes(200,"成功"),
	fail(201,"系统异常，请稍后重试"),
	needSignIn(202,"登录过期，请重新登录"),
	signFail(203,"签名错误"),
	networkError(204,"网络请求错误，未响应结果"),
	nonEnough(205,"您的账户积分不足，请选择其他方式支付"),
	nonExistEvent(206,"该赛事不存在，请重新确认或联系客服。"),
	existClosedEvent(206,"该赛事已截止报名，请重新确认您的报名信息。"),
	unCompleteOrder(207,"您有未支付的报名，请在 我的->订单列表 中先支付后或取消后再报名其他赛事。"),
	unKnowUser(208,"该用户不存在，请重新确认后或联系客服"),
	nonOrderEvent(209,"该订单不存在，请重新确认或联系客服。"),
	;
	
	ResultType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	private int code;
	private String desc;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
