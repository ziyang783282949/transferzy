package com.spirit.porker.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import freemarker.template.utility.StringUtil;

public class ParamUtil {

	/**
	 * http或https的get请求时拼接参数
	 * @param obj
	 * @return
	 * @throws IllegalAccessException
	 * @throws UnsupportedEncodingException
	 */
	public static String genHttpParam(Object obj) throws IllegalAccessException, UnsupportedEncodingException {
		
		if (obj == null) {
			return "";
		}
		
		if(obj instanceof String){
			return (String)obj;
		}
		
		StringBuilder sb = new StringBuilder();
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value != null) {
				sb.append(field.getName());
				sb.append("=");
				sb.append(URLEncoder.encode(value.toString(), "utf-8"));
				sb.append("&");
			}
		}
		
		if(StringUtils.isBlank(sb.toString())){
			return sb.toString();
		}
		
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static String genParam(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry entry : map.entrySet()) {
			sb.append(entry.getKey());
			sb.append("=");
			Object value = entry.getValue();
			if (value != null) {
				sb.append(value);
			}
			sb.append("&");
		}
		return sb.toString();
	}

}
