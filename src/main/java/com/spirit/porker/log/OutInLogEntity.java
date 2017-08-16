package com.spirit.porker.log;

import com.alibaba.fastjson.JSON;

public class OutInLogEntity  extends BaseLog{
	
	/**
	 * 请求接口路径
	 */
	private String path;
	
	/**
	 * 请求类名
	 */
	private String className;
	
	/**
	 * 请求方法名
	 */
	private String method;
	
	/**
	 * 入参
	 */
	private String req;
	
	/**
	 * 响应结果
	 */
	private String res;
	
	/**
	 * 耗时
	 */
	private long useTime;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}
	
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
	
}
