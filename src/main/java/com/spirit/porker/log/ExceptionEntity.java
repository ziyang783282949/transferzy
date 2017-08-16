package com.spirit.porker.log;

/** 
* @ClassName: ExceptionEntity 
* @Description: TODO(记录异常日志的实体类) 
* @author wenke.wang
* @date 2017年2月19日 下午5:47:19 
*  
*/
public class ExceptionEntity extends BaseLog{

	/**
	 * 异常信息描述
	 */
	private String msg;
	
	/**
	 * 异常堆栈信息
	 */
	private Exception exception;

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
