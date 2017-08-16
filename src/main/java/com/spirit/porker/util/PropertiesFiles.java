package com.spirit.porker.util;

import java.util.HashMap;
import java.util.Map;

public class PropertiesFiles {
	
	private static Map<String, FileUtil> filesMap = new HashMap<String, FileUtil>();
	
	public static FileUtil get(String fileName){
		return filesMap.get(fileName);
	}
	
	public static void put(String fileName,FileUtil fileUtil){
		filesMap.put(fileName, fileUtil);
	}
	
}
