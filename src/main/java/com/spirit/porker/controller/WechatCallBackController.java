package com.spirit.porker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spirit.porker.service.WechatCallBackService;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.response.WxPayCallBackResponse;

/**
 * @Description:微信支付结果回调接口
 * @author: tony.wang
 * @time:2017年7月31日 上午11:19:09
 */
@Controller
@RequestMapping("/wechat")
public class WechatCallBackController {
	
	@Resource
	WechatCallBackService wechatCallBackService;
	
	public String callBack(HttpServletRequest request,HttpServletResponse response){
		
		try{
			return wechatCallBackService.process(request, response);
		}catch (Exception e) {
			LoggerUtil.error("微信回调异常", e);
			return WxPayCallBackResponse.sysFail;
		}
	}
	
}


