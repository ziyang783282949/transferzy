package com.spirit.porker.service;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.OrderDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.model.OrderModel;
import com.spirit.porker.util.JsonXmlUtil;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.request.WxPayCallBackRequest;
import com.spirit.porker.vo.response.WxPayCallBackResponse;

/**
 * @Description:微信支付回调相关业务逻辑处理
 * @author: tony.wang
 * @time:2017年7月31日 下午1:52:24
 */
@Service
public class WechatCallBackService {
	
	@Resource
	OrderDao orderDao;
	
	/**
	 * 处理支付结果，做判重处理处理
	 * 1. 更改订单状态
	 * 2. 异步向用户发起微信消息推送通知（通知比赛内容）。
	 * 3. 同步更新奖池总金额和报名用户人数
	 * @throws Exception 
	 * 
	 */
	public String process(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		WxPayCallBackRequest pojo = buildPojo(request);
		if(pojo == null){
			return WxPayCallBackResponse.sysFail;
		}
		
		if(pojo.validate() != null){
			return WxPayCallBackResponse.paramFail;
		}
		
		Map<String, Object> cond = new HashMap<String,Object>();
		
		PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
		if(orders == null || orders.size() ==0 ){
			return WxPayCallBackResponse.orderNotExist;
		}
		
		return WxPayCallBackResponse.success;
	}
	
	public WxPayCallBackRequest buildPojo(HttpServletRequest request){
		StringBuffer xml = new StringBuffer();
		String line = null;
		try{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null){
				xml.append(line);
			}
			
		}catch (Exception e) {
			LoggerUtil.error("读取数据异常", e);
			return null;
		}
		
		try {
			Document document = DocumentHelper.parseText(xml.toString());
			WxPayCallBackRequest pojo = JsonXmlUtil.fromXmlToBean(document.getRootElement(),WxPayCallBackRequest.class);
			System.out.println(JSON.toJSONString(pojo));
			return pojo;
		} catch (DocumentException e) {
			LoggerUtil.error("解析微信支付回调xml异常：", e);
			return null;
		}catch (Exception e) {
			LoggerUtil.error("解析微信支付回调将xml转json异常：", e);
			return null;
		}
		
	}

}


