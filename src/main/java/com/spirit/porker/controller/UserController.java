package com.spirit.porker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("auth")
public class UserController extends BaseController{

	/*@Resource
	MeService meService;*/
	
	
	/*@RequestMapping(value = "/shop")
	@ResponseBody
	public String shop(ShowShopRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<ShowShopRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(ShowShopRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) meService.shop(pojo, servletRequest, servletResponse);
			}
		});
	}*/
	
}


