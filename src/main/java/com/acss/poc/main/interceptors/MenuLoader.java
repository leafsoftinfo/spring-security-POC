package com.acss.poc.main.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.acss.poc.account.Account;
import com.acss.poc.main.MenuItem;
import com.acss.poc.main.MenuService;

/**
 * Loads the menu list in request.
 * @author gvargas.local
 *
 */
public class MenuLoader implements HandlerInterceptor{
	
	@Autowired
	MenuService menuService;
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
	
	/**
	 * Adds the menulist in request scope.
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}
	

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		List<MenuItem> menuList = menuService.getMenuItem().getMenuItems();
		arg0.setAttribute("menuList", menuList);
		arg0.setAttribute("accountForm", new Account());
		return true;
	}
	
}
