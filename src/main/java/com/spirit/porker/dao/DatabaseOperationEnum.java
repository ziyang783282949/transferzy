package com.spirit.porker.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author user
 *
 */
public enum DatabaseOperationEnum {

	WRITE("WRITE"), READ("READ"), READWRITE("READWRITE"), DEFAULT("DEFAULT");
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	DatabaseOperationEnum(String value) {
		this.value = value;
	}

	private final static Map<String, DatabaseOperationEnum> typeMap = new HashMap<String, DatabaseOperationEnum>();

	static {
		for (DatabaseOperationEnum type : DatabaseOperationEnum.values())
			typeMap.put(type.getValue(), type);
	}

	public static Map<String, DatabaseOperationEnum> getEnumMap() {
		return typeMap;
	}

}
