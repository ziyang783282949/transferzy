package com.spirit.porker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spirit.porker.service.MeService;
import com.spirit.porker.service.ServiceHandler;
import com.spirit.porker.vo.request.MeRequest;
import com.spirit.porker.vo.request.ShowShopRequest;
import com.spirit.porker.vo.request.UserMiscRequest;
import com.spirit.porker.vo.request.UserTicketsRequest;
import com.spirit.porker.vo.response.BaseResponse;

@Controller
@RequestMapping("auth")
public class UserController extends BaseController{

	@Resource
	MeService meService;
	
	@RequestMapping(value = "/me")
	@ResponseBody
	public String login( MeRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<MeRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(MeRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) meService.myInfo(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/updateMe")
	@ResponseBody
	public String updateMe( MeRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<MeRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(MeRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) meService.updateMe(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/userTicket")
	@ResponseBody
	public String userTicket(UserTicketsRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<UserTicketsRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(UserTicketsRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) meService.userTicket(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/userMisc")
	@ResponseBody
	public String userMisc(UserMiscRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<UserMiscRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(UserMiscRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) meService.userMisc(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/shop")
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
	}
}


