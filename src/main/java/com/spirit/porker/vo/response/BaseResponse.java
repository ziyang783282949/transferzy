package com.spirit.porker.vo.response;

import com.spirit.porker.enums.ResultType;

public class BaseResponse<T> {

	private int code;

	private String desc;

	/**
	 * 数据体
	 */
	private T data;

	public BaseResponse(ResultType resultType) {
		this.code = resultType.getCode();
		this.desc = resultType.getDesc();
	}

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
