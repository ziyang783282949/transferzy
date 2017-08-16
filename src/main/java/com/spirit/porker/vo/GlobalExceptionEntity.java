package com.spirit.porker.vo;

import java.util.Date;

public class GlobalExceptionEntity {

	private String appServerIp;
	
	private Date logTime;
	
	private Exception exception;

	public String getAppServerIp() {
		return appServerIp;
	}

	public void setAppServerIp(String appServerIp) {
		this.appServerIp = appServerIp;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
}
