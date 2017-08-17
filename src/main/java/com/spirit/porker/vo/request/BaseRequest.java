package com.spirit.porker.vo.request;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import com.spirit.porker.annotation.NotEmpty;
import com.spirit.porker.vo.response.PojoValidatorErrors;

public class BaseRequest {
	/**
	 * 参数校验
	 * @return
	 * @throws Exception
	 */
	public PojoValidatorErrors validate() throws Exception{
		
		Field[] fields = this.getClass().getDeclaredFields();
		if(fields == null){
			return null;
		}
		
		for(Field field : fields){
			NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
			if(notEmpty != null){
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(),this.getClass());
	            Method getMethod = pd.getReadMethod();//获得get方法  
	            Object value = getMethod.invoke(this);//执行get方法返回一个Object
	            
				//string类型非空判定
				if(field.getType().equals(String.class)){
					field.setAccessible(true);
					 if(StringUtils.isBlank((String)value)){
						 PojoValidatorErrors errors = new PojoValidatorErrors();
						 errors.setError(true);
						 errors.setMsg(field.getName()+"字段不能为空");
						 return errors;
					 }
				}else {
					 if( value == null){
						 PojoValidatorErrors errors = new PojoValidatorErrors();
						 errors.setError(true);
						 errors.setMsg(field.getName()+"字段不能为空");
						 return errors;
					 }
				}
			}
		}
		
		return null;
	}

	
}
