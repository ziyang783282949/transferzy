package com.spirit.porker.agent;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 联合登录工厂，用户获取对应的服务service
 * @author user
 *
 */
public class UionOpenApiFactory {
	
	static{
		init();
	}
	
	private static Map<Integer,IOpenService> openMap = new HashMap<Integer,IOpenService>();
	
	public static IOpenService getServiceByType(int type){
		return openMap.get(type);
	}
	
	/**
	 * 初始化配置所有的联合登录信息，前期写入代码中，后期以IOC注入方式完成
	 */
	public static void init(){
	}

	public static Map<Integer, IOpenService> getOpenMap() {
		return openMap;
	}

	public static void setOpenMap(Map<Integer, IOpenService> openMap) {
		UionOpenApiFactory.openMap = openMap;
	}
	
}
