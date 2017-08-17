package com.spirit.porker.merchant.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.service.SettingService;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.request.SelectNameRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.SelectNameResponse;

@Controller
public class SettingController {

	@Resource
	SettingService settingService;


	
	
	/*@RequestMapping("/verifyBuyCredits")
	@ResponseBody
	public String VerifyBuyCredits(VerifyBuyCreditsRequest pojo) {

		BaseResponse<Object> result = null;

		try {
			result = settingService.verifyBuyCredits(pojo);
		} catch (Exception e) {
			LoggerUtil.error("微信回调购买积分失败", e);
			result = new BaseResponse<Object>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}*/
	
	@RequestMapping("/selectName")
	@ResponseBody
	public String selectName(SelectNameRequest pojo) {

		BaseResponse<SelectNameResponse> result = null;

		try {
			result = settingService.selectName(pojo);
		} catch (Exception e) {
			LoggerUtil.error("微信回调购买积分失败", e);
			result = new BaseResponse<SelectNameResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	
	}
}
