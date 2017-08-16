package com.spirit.porker.test;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spirit.porker.service.webservice.WxCallBackService;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({ "classpath:conf/core/porker-servlet.xml", "classpath:conf/core/webservice.xml" }) // 加载配置文件
public class WxCallBackTest {
	
	public String endpoint = "localhost:8080/service/wx/payCallBack?wsdl";
	
//	@Test
	public void wxCallBackTest() throws MalformedURLException{
		
		URL url = new URL("http://localhost:8080/service/wx/payCallBack?wsdl");
		
		 String xml="<?xml version='1.0' encoding='UTF-8'?>" +
		"<ROOT><datas id='1'><main>" +
		"<data name='name'>XHC/YJ/ZZ</data>" +
		"<data name='type'>201505</data>" +
		"<data name='openid'>10002083</data>" +
		"<data name='mch_id'>2015-05-08</data>" +
		"</detail></details></datas></ROOT>";
		 
//		 Service service = new Service(url, new QName(""));
		 
	}
	
	/**
	 * 浏览器访问http://localhost:8080/service/出现webservice相关信息
	 * 类似于tomcat的首页
	 */
	@Test
	public void test2(){
		
		try {
			URL url = new URL("http://localhost:8080/service/wx/payCallBack?wsdl");
			QName sname=new QName("http://webservice.service.porker.spirit.com/","WxCallBack");
	        Service service=Service.create(url, sname);
	        WxCallBackService hell = service.getPort(WxCallBackService.class);
	        
	        String xml="<xml>\n" + 
	        		"  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" + 
	        		"  <attach><![CDATA[支付测试]]></attach>\n" + 
	        		"  <bank_type><![CDATA[CFT]]></bank_type>\n" + 
	        		"  <fee_type><![CDATA[CNY]]></fee_type>\n" + 
	        		"  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" + 
	        		"  <mch_id><![CDATA[10000100]]></mch_id>\n" + 
	        		"  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" + 
	        		"  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" + 
	        		"  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" + 
	        		"  <result_code><![CDATA[SUCCESS]]></result_code>\n" + 
	        		"  <return_code><![CDATA[SUCCESS]]></return_code>\n" + 
	        		"  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" + 
	        		"  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" + 
	        		"  <time_end><![CDATA[20140903131540]]></time_end>\n" + 
	        		"  <total_fee>1</total_fee>\n" + 
	        		"  <trade_type><![CDATA[JSAPI]]></trade_type>\n" + 
	        		"  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" + 
	        		"</xml>";
	        
	        System.out.println(hell.payCallBack(xml));
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}

