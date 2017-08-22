package com.spirit.porker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.UserDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.model.UserModel;
import com.spirit.porker.service.ServiceHandler;
import com.spirit.porker.util.AES;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.request.BaseRequest;
import com.spirit.porker.vo.response.BaseResponse;

public abstract class BaseController {

	@Resource
	UserDao userDao;

	@SuppressWarnings("unchecked")
	public <T extends BaseRequest> String doMain(T pojo,HttpServletRequest servletRequest,
			HttpServletResponse servletResponse,ServiceHandler<T> handler){
		
		BaseResponse<Object> response = new BaseResponse<Object>(ResultType.succes);
		try{
			Cookie[] cookie = servletRequest.getCookies();
			for(int i=0;i<cookie.length;i++) {
				if(cookie[i].getName().equals("sessionId")) {
					//判定查询数据库
					Map<String,Object> cond=new HashMap<>();
					cond.put("session", cookie[i].getValue());
					PaginationList<UserModel> users=userDao.findEntityListByCond(cond, null);
					if(users == null && users.size()==0) {
						response.setCode(ResultType.needSignIn.getCode());
						response.setDesc(ResultType.needSignIn.getDesc());
						response.setData(new Object());
						return JSON.toJSONString(response);
					}
					
				}
				
			}
			
			Cookie myCo = new Cookie("sessionId","sdfjskdfjskdfjkdlksjf");
			servletResponse.addCookie(myCo);
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
