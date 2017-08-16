package com.spirit.porker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.service.ServiceHandler;
import com.spirit.porker.util.AES;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.request.BaseRequest;
import com.spirit.porker.vo.response.BaseResponse;

public abstract class BaseController {
	
	@SuppressWarnings("unchecked")
	public <T extends BaseRequest> String doMain(T pojo,HttpServletRequest servletRequest,
			HttpServletResponse servletResponse,ServiceHandler<T> handler){
		
		BaseResponse<Object> response = new BaseResponse<Object>(ResultType.succes);
		try{
			response = handler.doService(pojo,servletRequest,servletResponse);
			return JSON.toJSONString(response);
		
		}catch (Exception e) {
			LoggerUtil.error("执行任务异常", e);
			response.setCode(ResultType.fail.getCode());
			response.setDesc(ResultType.fail.getDesc());
			response.setData(new Object());
			return JSON.toJSONString(response);
		}
	}

}
