package com.spirit.porker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.model.UserModel;
import com.spirit.porker.service.SettingService;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.LoginResponse;

@Controller
public class LoginController {

	@Resource
	SettingService settingService;

	/*
	 * @RequestMapping(value = "/shop")
	 * 
	 * @ResponseBody public String shop(ShowShopRequest pojo,HttpServletRequest
	 * servletRequest,HttpServletResponse servletResponse){ return
	 * super.doMain(pojo, servletRequest, servletResponse, new
	 * ServiceHandler<ShowShopRequest>() {
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public <T extends BaseResponse> T doService(ShowShopRequest pojo,
	 * HttpServletRequest servletRequest, HttpServletResponse servletResponse)
	 * throws Exception { return (T) meService.shop(pojo, servletRequest,
	 * servletResponse); } }); }
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody UserModel pojo,HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

		BaseResponse<LoginResponse> result = null;

		try {
			result = settingService.login(pojo,servletRequest,servletResponse);
		} catch (Exception e) {
			LoggerUtil.error("登录失败", e);
			result = new BaseResponse<LoginResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);

	}
	@RequestMapping(value = "/as", method = RequestMethod.POST)
	@ResponseBody
	public String as(@RequestBody UserModel pojo,HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

		BaseResponse<LoginResponse> result = new BaseResponse<>(ResultType.succes);

		return JSON.toJSONString(result);

	}
}
