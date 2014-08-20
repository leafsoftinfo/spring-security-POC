package com.acss.poc.core.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class KickAssTransactionLogHandler {
	private static Logger auditLogger = LoggerFactory.getLogger("audit");
	
	/**
	 * Audit message for save method on repositories.
	 * @param returnValue
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@AfterReturning(pointcut ="execution(* com.acss.poc.*..*Repository+.save*(..)) "
			,returning="returnValue")
	public void logSaveTransaction(Object returnValue) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		auditLogger.info(describeBean(returnValue) + " has been successfully saved/updated.");
	}
	
	/**
	 * Describes the content of a bean for logging and debugging purposes.
	 * @param bean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private String describeBean(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		StringBuilder builder = new StringBuilder();
		builder.append("Domain : "+bean.toString());
		@SuppressWarnings("unchecked")
		Map<String, Object> properties = BeanUtils.describe(bean);
		//excluded fields.
		properties.remove("class");
		properties.remove("password");
		
		for(String key : properties.keySet()){
			Object value = properties.get(key);
			builder.append(key + " : " +value + " | ");
		}
		return builder.toString();
	}
}
