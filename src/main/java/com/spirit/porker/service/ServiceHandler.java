package com.spirit.porker.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spirit.porker.vo.request.BaseRequest;
import com.spirit.porker.vo.response.BaseResponse;

/**
 * 具体的业务实现handler
 * @author user
 * @param <T>
 */
public abstract class ServiceHandler<M extends BaseRequest> {
	
	public abstract <T extends BaseResponse> T doService(M pojo,HttpServletRequest servletResquest,
			HttpServletResponse servletResponse) throws Exception;

}
