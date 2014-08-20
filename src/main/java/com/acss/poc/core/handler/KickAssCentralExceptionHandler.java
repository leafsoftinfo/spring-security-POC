package com.acss.poc.core.handler;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.acss.poc.account.Account;
import com.acss.poc.core.Message;
import com.acss.poc.main.exception.MenuServiceException;

/**
 * This Handlder is so awesome that 
 * it centralized the logging mechanism all accross the code.
 * On top of that it could also present the exception page so awesome!
 * 
 * so we are focused on code and not being distracted with logger object all over
 * the place in our code.
 * 
 * @author gvargas.local
 *
 */
@Component
@Aspect
public class KickAssCentralExceptionHandler {

	private static Logger errorLogger = LoggerFactory.getLogger("errors");
	
	
	/** 
	 * Handles the exception logging and view..
	 * A mandatory for controllers that may throw exception.
	 * always return a ModelAndView Object.
	 * 
	 * 
	 * @param jp
	 * @param controller
	 * @return
	 * @throws Throwable
	 */
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && target(controller)")
    public Object handleException(ProceedingJoinPoint jp, Object controller) throws Throwable {
        Object view = null;
        Message msg = new Message();
        
        try {
            view = (Object) jp.proceed();        
        
        } catch (DataAccessResourceFailureException | MenuServiceException e) {
        	errorLogger.error("error in {}", controller.getClass().getSimpleName(), e);
            msg.setInfo(e.getMessage());
            msg.setIsError(Message.TRUE);
            msg.setExceptionMsg(ExceptionUtils.getFullStackTrace(e));
            return new ModelAndView("exceptionpage", "message", msg);
        }
        return view;
    }
    
    @Around("execution(* com.acss.poc.*..AccountJdbcRepository+.find*(..)) && target(repo)")
    public Account handleRepoException(ProceedingJoinPoint jp, Object repo) throws Throwable {
        Message msg = new Message();
        Account returnAccount = null;
        try {
        	returnAccount = (Account) jp.proceed();
        } catch (DataAccessResourceFailureException e) {
            errorLogger.error("error in {}", repo.getClass().getSimpleName(), e);
            msg.setInfo(e.getMessage());
            msg.setIsError(Message.TRUE);
            return null;
        }catch (EmptyResultDataAccessException e) {
            return null;
		}catch (BadSqlGrammarException e) {
            errorLogger.error("error in {}", repo.getClass().getSimpleName(), e);
            msg.setInfo(e.getMessage());
            msg.setIsError(Message.TRUE);
            return null;
		}
        
        return returnAccount;
    }

}
