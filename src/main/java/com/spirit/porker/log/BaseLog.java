package com.spirit.porker.log;

import java.sql.Timestamp;

public class BaseLog {
	
	/**
	 * 追踪Id，用于分析一个用户的行为。详见traceId生成方法
	 */
	private String traceId;
	
	/**
	 * 日志发生时间，即记录时间。
	 */
	private Timestamp timestamp;

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
