package com.spirit.porker.util;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.model.MessageModel;

/** 
* @ClassName: LoggerUtil 
* @Description: TODO(日志方法)
* @author wenke.wang
* @date 2017年2月19日 下午5:44:20 
*  
*/
public class LoggerUtil {
	
	public static void inOut(String msg){
		Logger.getLogger("inOut").info(msg);
	}
	
	public static void outIn(Object msg){
		Logger.getLogger("outIn").info(msg);
	}
	
	public static void error(Object msg,Exception e){
		Logger.getLogger("error").info(msg,e);
	}
	
	public static void error(Object msg,Throwable e){
		Logger.getLogger("error").info(msg,e);
	}
	
	public static void action(String msg){
		Logger.getLogger("action").info(msg);
	}
	
	public static void message(String content,String title,String description,String result){
		MessageModel messageBody = new MessageModel();
		messageBody.setContent(content);
		messageBody.setTitle(title);
		messageBody.setDescription(description);
		LoggerUtil.inOut("{\"messageBody\":"+JSON.toJSONString(messageBody)+",{\"result\":"+result+"}");
	}
	

}
