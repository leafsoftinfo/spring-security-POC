package com.acss.poc.main;

import javax.xml.bind.JAXBException;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acss.poc.core.AwesomeBaseController;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages. Only the admin can see
 * the adminpage, however.
 */
@Controller
@RequestMapping("/main")
public class MainController extends AwesomeBaseController{
	
	
	
	/**
	 * View Definitions
	 */
	private static String HOME_PAGE="commonpage";
	private static String USER_PAGE="userpage";
	private static String ADMIN_PAGE="adminpage";
	private static String DENIED_PAGE="deniedpage";
	
	
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 * @throws JAXBException 
	 */
	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String getCommonPage(){
		return HOME_PAGE;
	}

	/**
	 * Handles and retrieves the admin JSP page that only admins can see
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminPage() {
		return ADMIN_PAGE;
	}
	
	/**
	 * Handles and retrieves the user JSP page that user can see
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public String getUserPage(){
		return USER_PAGE;
	}
	
	/**
	 * Handles denied access. This is shown whenever a response code: 403
	 * 
	 * @return the name of the JSP page
	*/
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage(Authentication auth,ModelMap model) {
		addErrorMessage(model, "Access denied on page requesting...");
		return auth instanceof AnonymousAuthenticationToken?DENIED_PAGE:HOME_PAGE;
	} 
	
}
