package com.spirit.porker.service.webservice;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.service.WechatCallBackService;
import com.spirit.porker.util.JsonXmlUtil;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.request.WxPayCallBackRequest;
import com.spirit.porker.vo.response.WxPayCallBackResponse;

@WebService(endpointInterface="com.spirit.porker.service.webservice.WxCallBackService",serviceName="WxCallBack")
public class WxCallBackServiceImp implements WxCallBackService{
	
	@Resource
	WechatCallBackService wechatCallBackService;

	@Override
	public String payCallBack(String xml) {
		
		try {
			Document document = DocumentHelper.parseText(xml);
			WxPayCallBackRequest pojo = JsonXmlUtil.fromXmlToBean(document.getRootElement(),WxPayCallBackRequest.class);
			System.out.println(JSON.toJSONString(pojo));
			if(pojo.validate() != null){
				return WxPayCallBackResponse.paramFail;
			}
//			wechatCallBackService.process(pojo);
			
		} catch (DocumentException e) {
			LoggerUtil.error("解析微信支付回调xml异常：", e);
			return WxPayCallBackResponse.sysFail;
		}catch (Exception e) {
			LoggerUtil.error("解析微信支付回调将xml转json异常：", e);
			return WxPayCallBackResponse.sysFail;
		}
		
		return WxPayCallBackResponse.success;
	}
	
	public static void main(String[] args){
		WxPayCallBackRequest pojo = new WxPayCallBackRequest();
		System.out.println(JSON.toJSONString(pojo));
		
	}
	
}


