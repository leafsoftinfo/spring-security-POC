package com.acss.poc.remote;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.acss.hps.core.ValidationResult;
import com.acss.poc.account.IAccountService;

@Controller
@RequestMapping("remote")
public class ValidatorController {
	
	private IAccountService accountService;
	
	public ValidatorController() {}
	
	@Autowired
	public ValidatorController(IAccountService accountService){
		this.accountService = accountService;
	}
	
	/**
	 * Remote Validator
	 * Checks if the username is taken or not.
	 * @param username
	 * @return
	 * @throws IOException 
	 */
    @RequestMapping(value="checkUserName", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String ifExist(@RequestParam(value="username") String username) throws IOException{
    	
    	if(accountService.findByUsername(username)!=null){
    		return ValidationResult.FAILED.getValue();
    	}else
    		return ValidationResult.SUCCESS.getValue();
    	
    }
    
}
