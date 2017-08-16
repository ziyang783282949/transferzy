package com.spirit.porker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spirit.porker.service.EventService;
import com.spirit.porker.service.ServiceHandler;
import com.spirit.porker.vo.request.EventDetailRequest;
import com.spirit.porker.vo.request.EventListRequest;
import com.spirit.porker.vo.response.BaseResponse;

@Controller
@RequestMapping("auth")
public class ProductController extends BaseController{
	
	@Resource
	EventService eventService;
	
	@RequestMapping(value = "/events")
	@ResponseBody
	public String login( EventListRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<EventListRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(EventListRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) eventService.getEvents(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/eventDetail")
	@ResponseBody
	public String eventDetail( EventDetailRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<EventDetailRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(EventDetailRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) eventService.getEventDetail(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	
}
