package com.acss.poc.core.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class KickAssCentralMonitoringHandler {
	private static Logger monitorLogger = LoggerFactory.getLogger("monitoring");
	
	public KickAssCentralMonitoringHandler() {}
		
    
    @Around("execution(* com.acss.poc.*..*Service+.*(..)) && target(service)")
    public Object logServiceAccess(ProceedingJoinPoint jp, Object service) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = jp.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        monitorLogger.info("{} - {} | Invocation time {}ms ",service.getClass().getSimpleName(),jp.getSignature().getName(), totalTime);
        
        return result;
    }
}
