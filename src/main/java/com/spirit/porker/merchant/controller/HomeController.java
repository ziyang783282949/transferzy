package com.spirit.porker.merchant.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.UserDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.model.AdminModel;
import com.spirit.porker.model.UserModel;
import com.spirit.porker.vo.request.LoginRequest;
import com.spirit.porker.vo.response.BaseResponse;

@Controller
@RequestMapping(value = "admin")
public class HomeController {

	@Resource
	UserDao userDao;

	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(LoginRequest pojo, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		BaseResponse<Object> result = new BaseResponse<>(ResultType.succes);
		try {
			if (pojo.validate() != null) {
				result.setCode(ResultType.signFail.getCode());
				result.setDesc(pojo.validate().getMsg());
				return JSON.toJSONString(result);
			}
			// 使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
			redirectAttributes.addFlashAttribute("message", pojo.getUsername());
			SecurityUtils.getSubject().login(new UsernamePasswordToken(pojo.getUsername(), pojo.getPassword()));
			
			result.setDesc("成功");
			return JSON.toJSONString(result);
		} catch (AuthenticationException e) {
			result.setCode(ResultType.signFail.getCode());
			result.setDesc("用户名或密码错误");
		} catch (Exception e) {
			result.setCode(ResultType.signFail.getCode());
			result.setDesc("系统异常");
		}
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:login";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		return "403";
	}

}
