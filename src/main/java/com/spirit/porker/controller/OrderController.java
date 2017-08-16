package com.spirit.porker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spirit.porker.service.OrderService;
import com.spirit.porker.service.PayService;
import com.spirit.porker.service.ServiceHandler;
import com.spirit.porker.vo.request.OrderDetailRequest;
import com.spirit.porker.vo.request.OrderListRequest;
import com.spirit.porker.vo.request.PayRequest;
import com.spirit.porker.vo.request.SubmitOrderRequest;
import com.spirit.porker.vo.response.BaseResponse;

@Controller
@RequestMapping("auth")
public class OrderController extends BaseController{
	
	@Resource
	OrderService orderService;
	
	@Resource
	PayService payService;

	@RequestMapping(value = "/orderCreate")
	@ResponseBody
	public String login( SubmitOrderRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<SubmitOrderRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(SubmitOrderRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) orderService.create(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/continuePay")
	@ResponseBody
	public String continuePay( PayRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<PayRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(PayRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) payService.continuePay(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/orderList")
	@ResponseBody
	public String orderList( OrderListRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<OrderListRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(OrderListRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) orderService.orderList(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/orderDetail")
	@ResponseBody
	public String orderDetail( OrderDetailRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<OrderDetailRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(OrderDetailRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) orderService.orderDetail(pojo, servletRequest, servletResponse);
			}
		});
	}
	

	@RequestMapping(value = "/order/rebuy")
	@ResponseBody
	public String orderRebuy( OrderDetailRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<OrderDetailRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(OrderDetailRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) orderService.orderDetail(pojo, servletRequest, servletResponse);
			}
		});
	}
	

	@RequestMapping(value = "/order/pay")
	@ResponseBody
	public String pay( PayRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<PayRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(PayRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) payService.continuePay(pojo, servletRequest, servletResponse);
			}
		});
	}
	
}


