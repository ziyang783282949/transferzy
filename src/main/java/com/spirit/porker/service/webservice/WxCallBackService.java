package com.spirit.porker.service.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.spirit.porker.vo.request.WxPayCallBackRequest;

@WebService
public interface WxCallBackService {
	
	@WebMethod
	public String payCallBack(String xml);

}


