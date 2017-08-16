package com.spirit.porker.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil implements java.io.Serializable {

	/**
	 * Default SID
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(FileUtil.class);

	private String fileName;

	private Properties prop;

	private Properties propReverse = new Properties();

	private InputStream is;

	public enum INDEX {
		FIRST, LAST
	}

	public static FileUtil getInstance(String fileName) {
		String[] splits = fileName.split("/");
		String fName = splits[splits.length - 1];
		if (PropertiesFiles.get(fName) != null) {
			return PropertiesFiles.get(fName);
		}
		return new FileUtil(fileName);
	}

	/**
	 * 构造函数
	 *
	 * @param filename
	 */
	private FileUtil(String filename) {
		prop = new Properties();
		fileName = filename;
		init();
		String[] splits = filename.split("/");
		String fName = splits[splits.length - 1];
		PropertiesFiles.put(fName, this);
	}

	public void reload() {
		init();
	}

	private void init() {
		try {
			is = getClass().getResourceAsStream(fileName);
			prop.load(is);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
		}
	}

	/**
	 * 初始化逆向文件
	 */
	private synchronized void initReverse() {
		for (Iterator<Entry<Object, Object>> iter = prop.entrySet().iterator(); iter.hasNext();) {
			Entry<Object, Object> entry = iter.next();
			if (null != entry.getValue()) {
				propReverse.put(entry.getValue(), entry.getKey());
			}
		}
	}

	/**
	 * 取得属性
	 *
	 * @param propertyName
	 *            属性名
	 * @return 属性值
	 */
	public String getProperties(String propertyName) {
		return prop.getProperty(propertyName);
	}

	/**
	 * 取得属性
	 *
	 * @param propertyName
	 *            属性名
	 * @return 属性值
	 */
	public int getPropertiesInt(String propertyName) {
		int value = -1;
		String val = prop.getProperty(propertyName);
		try {
			value = Integer.parseInt(val);
		} catch (Exception e) {
//			LogRecordUtil.error("getPropertiesInt异常" + propertyName + ":" + val, e);
		}
		return value;
	}

	/**
	 * 取得属性
	 *
	 * @param propertyName
	 *            属性名
	 * @return 属性值
	 */
	public Boolean getPropertiesBool(String propertyName) {
		Boolean bool = null;
		String val = prop.getProperty(propertyName);
		if (val.equals("true")) {
			bool = true;
		} else if (val.equals("false")) {
			bool = false;
		}
		return bool;
	}

	/**
	 * 取得key-set
	 *
	 * @return key-set
	 */
	@SuppressWarnings("unchecked")
	public Enumeration<String> propertyNames() {
		return (Enumeration<String>) prop.propertyNames();
	}

	/**
	 * 根据属性值获取key
	 *
	 * @param propertyValue
	 *            属性值
	 * @return 属性名
	 */
	public String getPropKeyByVal(String propertyValue) {
		if (null == propertyValue)
			return null;
		if (null == propReverse)
			initReverse();
		String propertyName = propReverse.getProperty(propertyValue);
		if (StringUtils.isBlank(propertyName)) {
			for (Iterator<Entry<Object, Object>> iter = prop.entrySet().iterator(); iter.hasNext();) {
				Entry<Object, Object> entry = iter.next();
				if (propertyValue.equals(entry.getValue())) {
					propertyName = entry.getKey().toString();
				}
			}
		}
		return propertyName;
	}

	/**
	 * 获取全部键值对
	 *
	 * @return 全部键值对
	 */
	public Set<Entry<Object, Object>> getPropertis() {
		return prop.entrySet();
	}

	public Hashtable<Object, Object> getPropertyFile() {
	    return prop;
	}
}