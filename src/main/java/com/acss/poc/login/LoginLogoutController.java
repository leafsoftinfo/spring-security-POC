package com.acss.poc.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acss.hps.core.AwesomeBaseController;
import com.acss.poc.account.Account;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController extends AwesomeBaseController{
	
	
	/**
	 * View Definitions
	 */
	private static String LOGIN_PAGE="loginpage";
	
	//dun know why always demand for this.
	public LoginLogoutController() {}
	
	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		//for registration on login screen.
		return LOGIN_PAGE;
	}
	

	/**
	 * Handles redirection to login page after logout.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
 	public String logoutSuccess(@ModelAttribute(value="account") Account account,ModelMap model) {
		String message = "Logout Success!";
		addInfoMessage(model, message);
		//for registration on login screen.
		return LOGIN_PAGE;
	}
	
	/**
	 * Handles redirection to login page after failed login
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/failed", method = RequestMethod.GET)
 	public String loginFailed(ModelMap model) {
		String message = "You have entered an invalid username or password!";
		addErrorMessage(model, message);
		return LOGIN_PAGE;
	}
	
	/**
	 * Handles redirection to login page after failed login
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/session-failed", method = RequestMethod.GET)
 	public String sessionFailed(ModelMap model) {
		String message = "You're current session has been invalidated due to concurrent login.";
		addErrorMessage(model, message);
		return LOGIN_PAGE;
	}
	
	
}