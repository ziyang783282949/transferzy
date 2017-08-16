package com.spirit.porker.aop;

import java.sql.Timestamp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
 
import com.alibaba.fastjson.JSON;
import com.spirit.porker.log.OutInLogEntity;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.request.BaseRequest;

/**
 * 主要用户记录日志信息，请求和响应结果
 * 
 * @author spirit-two
 *
 */
@Aspect
@Component
public class ControllerAspect {

	@Pointcut(value = "execution( * com.spirit.porker.controller.ProductController..*(..))")
	public void recordLog() {
	}

	@Around(value = "execution( * com.spirit.porker.controller.ProductController..*(..))")
	public Object around(ProceedingJoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			LoggerUtil.error("around exception", e);
		}
		
		OutInLogEntity logEntity = buildOutInEntity(joinPoint,(String)result);
		logEntity.setUseTime(System.currentTimeMillis()-startTime);
		LoggerUtil.outIn(logEntity);
		return result;
	}
	
	/** 
	* @Title: buildOutInEntity 
	* @Description: TODO(outin日志) 
	* @param @param joinPoint
	* @param @param result
	* @param @return    设定文件 
	* @return OutInLogEntity    返回类型 
	* @throws 
	*/
	public OutInLogEntity buildOutInEntity(JoinPoint joinPoint,String result){
		OutInLogEntity entity = new OutInLogEntity();
		entity.setClassName(joinPoint.getTarget().getClass().getName());
		entity.setMethod(joinPoint.getSignature().getName());
		for(Object object : joinPoint.getArgs()){
			if(object instanceof BaseRequest){
				entity.setReq(JSON.toJSONString(object));
				break;
			}
		}
		entity.setRes(result);
		entity.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return entity;
	}

}
