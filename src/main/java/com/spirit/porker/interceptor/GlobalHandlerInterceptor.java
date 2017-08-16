package com.spirit.porker.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spirit.porker.util.IPUtil;
import com.spirit.porker.vo.GlobalExceptionEntity;

public class GlobalHandlerInterceptor extends HandlerInterceptorAdapter {

	@SuppressWarnings("finally")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			GlobalExceptionEntity exception = new GlobalExceptionEntity();
			exception.setAppServerIp(IPUtil.getServerIp());
			exception.setLogTime(Calendar.getInstance().getTime());
			exception.setException(e);
			Logger.getLogger("globalExceptionLogger").info(exception.toString());
		} finally {
			// System.out.println("exit preHandle");
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) {
		// TODO Auto-generated method stub
		try {
			// System.out.println("enter postHandle");
		} catch (Exception e) {
			// TODO: handle exception
			GlobalExceptionEntity exception = new GlobalExceptionEntity();
			exception.setAppServerIp(IPUtil.getServerIp());
			exception.setLogTime(Calendar.getInstance().getTime());
			exception.setException(e);
			Logger.getLogger("globalExceptionLogger").info(exception.toString());
		} finally {
			// System.out.println("enter postHandle");
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
		try {
			// System.out.println("enter afterCompletion");
			// 处理全局异常
			if (e != null) {
				GlobalExceptionEntity exception = new GlobalExceptionEntity();
				exception.setAppServerIp(IPUtil.getServerIp());
				exception.setLogTime(Calendar.getInstance().getTime());
				exception.setException(e);
				Logger.getLogger("globalExceptionLogger").info(exception.toString());
			}
		} catch (Exception exp) {
			// TODO: handle exception
			GlobalExceptionEntity exception = new GlobalExceptionEntity();
			exception.setAppServerIp(IPUtil.getServerIp());
			exception.setLogTime(Calendar.getInstance().getTime());
			exception.setException(e);
			Logger.getLogger("globalExceptionLogger").info(exception.toString());
		} finally {
			// System.out.println("enter afterCompletion");
		}
	}

}