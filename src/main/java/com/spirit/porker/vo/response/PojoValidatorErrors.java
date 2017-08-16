package com.spirit.porker.vo.response;


/**
 * @Description:参数校验结果类
 * @author: tony.wang
 * @time:2017年8月1日 下午6:32:15
 */
public class PojoValidatorErrors {
	
	/**
	 * true表示有错误，错误信息在msg中
	 * false没有错误，msg为空
	 */
	private boolean isError;
	
	private String msg;

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}


