package com.spirit.porker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spirit.porker.service.ServiceHandler;
import com.spirit.porker.vo.request.LoginRequest;
import com.spirit.porker.vo.response.BaseResponse;


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
	/*@RequestMapping(value = "/login")
	@ResponseBody
	public String updateMe( LoginRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<LoginRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(LoginRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) meService.updateMe(pojo, servletRequest, servletResponse);
			}
		});
	}*/
}


