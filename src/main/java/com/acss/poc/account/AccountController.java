package com.acss.poc.account;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acss.hps.core.AwesomeBaseController;
import com.acss.hps.core.exception.MenuServiceException;


@Controller
@RequestMapping("account")
public class AccountController extends AwesomeBaseController{
	
    private IAccountService accountService;
	
	public AccountController(){}
	
    @Autowired
	public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }
    
    /**
     * Gets account information of the currently logged in user.
     * @param principal
     * @return
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "current", method = RequestMethod.GET)
    public String accounts(Principal principal,ModelMap model) {
        Assert.notNull(principal);
        model.addAttribute("account",accountService.findByUsername(principal.getName()));
        return "accountpage";
    }
    
    @Secured("ROLE_USER")
    @RequestMapping(value = "exception", method = RequestMethod.GET)
    public ModelAndView exceptionDemo() throws MenuServiceException{
    	throw new MenuServiceException("Demo for Exception Logs and Exception Handing. . .");
    }
    
    @Secured("ROLE_USER")
    @RequestMapping(value= "save", method = {RequestMethod.POST})
    public String save(Account account,RedirectAttributes redirectAttributes){
		accountService.saveOrUpdate(account);
		addFlashInfoMessage(redirectAttributes, "Account successfully updated.");
    	return "redirect:/main/common";
    }
    
    /**
     * Viewable by public.
     * @param account
     * @param model
     * @return
     */
    @RequestMapping(value= "signup", method= RequestMethod.POST)
    public String create(@ModelAttribute(value="account") Account account,RedirectAttributes redirectAttributes){
    	accountService.saveOrUpdate(account);
    	//reset the model Attribute
    	account = new Account();
    	addFlashInfoMessage(redirectAttributes, "Account Creation Success!");
    	return "redirect:/auth/login";
    }
}
