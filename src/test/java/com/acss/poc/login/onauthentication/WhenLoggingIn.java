package com.acss.poc.login.onauthentication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.acss.poc.login.LoginLogoutController;
import com.acss.poc.main.MainController;


/**
 * An Attempt for the Structured Unit/Integration Testing.
 * package level convention: On<Component Name>
 * class level convention: When<Action Perform> <Feature Name>
 * method level convention: should<Expected Results> On <Scenario Condition>
 * 
 * @author gvargas.local
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(locations = { "classpath:META-INF/spring/business-config.xml",
									"classpath:META-INF/spring/spring-security.xml",
								   "classpath:META-INF/spring/spring-securityPOC-servlet.xml"})
public class WhenLoggingIn{
		
	@Autowired
	WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).addFilter(springSecurityFilterChain,"/*").alwaysDo(print()).build();
	}
	
	@Test
	public void shouldGoToLoginPageOnCommonUrlAccess() throws Exception{
		//Arrange
		String expectedURL = "http://localhost/auth/login";
		
		//Act and Assert
		//common url
		mockMvc.perform(get("/main/common")
	            .contentType(MediaType.TEXT_HTML)
	            .accept(MediaType.TEXT_HTML))
	            //302 for redirection
	            .andExpect(status().is(302))
	            //redirected On.
	            .andExpect(redirectedUrl(expectedURL))
	            .andExpect(forwardedUrl(null));
		
	}
		
	@Test
	public void shouldGoToLoginPageOnNoAuthentication() throws Exception{
		//Arrange
		String expectedURL = "http://localhost/auth/login";
		//Act and Assert
		System.out.println("shouldGoToLoginPageOnNoAuthentication");
		mockMvc.perform(get("/main/admin")
	            .contentType(MediaType.TEXT_HTML)
	            .accept(MediaType.TEXT_HTML))
	            //redirected
	            .andExpect(status().is(302))
	            .andExpect(redirectedUrl(expectedURL))
	            .andExpect(forwardedUrl(null));
	}
	
	@Test
	public void shouldGoToCommonPageOnValidCredentials() throws Exception{
		//Arrange
		String username="admin";
		String password="admin";
		
		//Act and Assert
		/* instead of the default j_spring_security_check
		   I have defined a custom url to hide the spring security impl.
		   used login-check instead
		*/
		 mockMvc.perform(post("/login-check")
				.param("username",username).param("password",password))
	            //redirected
	            .andExpect(status().is(302))
	            .andExpect(redirectedUrl("/"))
	            .andExpect(forwardedUrl(null));
	}
	
	@Test
	public void shouldGoToLoginPageOnInvalidCredentials() throws Exception{
		//Arrange
		String username="admin";
		//wrong password.
		String password="password";
		
		//Act and Assert
		mockMvc.perform(post("/login-check")
				.param("username",username).param("password",password))
	            //redirected
	            .andExpect(status().is(302))
	            .andExpect(redirectedUrl("/auth/failed"))
	            .andExpect(forwardedUrl(null));
	}
	
	@Test
	public void shouldShowLoginPageOnLoginUrlAccess() throws Exception{
		//Act and Assert
		
		mockMvc.perform(get("/auth/login")
	            .contentType(MediaType.TEXT_HTML)
	            .accept(MediaType.TEXT_HTML))
	            .andExpect(handler().handlerType(LoginLogoutController.class))
	            .andExpect(status().isOk())
	            .andExpect(redirectedUrl(null))
	            .andExpect(forwardedUrl("/WEB-INF/jsp/loginpage.jsp"));
	}
	
	@Test
	public void shouldAccessAdminPageOnValidCredentials() throws Exception{
		
		//arrange
		final String adminPage = "/WEB-INF/jsp/adminpage.jsp";
		//admin has both user role and admin role.
		Authentication authentication =
                new UsernamePasswordAuthenticationToken("admin", "admin");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        //sets security context in session.
        MockHttpSession session = setSecurityContextInSession(securityContext);
        
        //attempt to access admin url
        //act and asserts
		mockMvc.perform(get("/main/admin")
				.session(session)
	            .contentType(MediaType.TEXT_HTML)
	            .accept(MediaType.TEXT_HTML))
	            .andExpect(handler().handlerType(MainController.class))
	            .andExpect(status().isOk())
	            .andExpect(redirectedUrl(null))
	            .andExpect(forwardedUrl(adminPage));
	}
	
	@Test
	public void shouldReturnAccessDeniedPageOnInvalidRole() throws Exception{
		//arrange
		final String deniedPage = "/main/denied";
		//user is having 'user role'
		Authentication authentication =
                new UsernamePasswordAuthenticationToken("user", "user");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        //sets security context in session.
        MockHttpSession session = setSecurityContextInSession(securityContext);
        
        //attempt to access admin url
        //act and asserts
		mockMvc.perform(get("/main/admin")
				.session(session)
	            .contentType(MediaType.TEXT_HTML)
	            .accept(MediaType.TEXT_HTML))
	            //denied status code.
	            .andExpect(status().is(403))
	            .andExpect(redirectedUrl(null))
	            .andExpect(forwardedUrl(deniedPage));
	}
	
	@Test
	public void shouldStayOnCommonPageOnWhileLoggedIn() throws Exception{
		//Arrange
		//common page which will be redirected into while logged in and not login page.
		String redirectedTo = "../main/common";
		//user is having 'user role'
		Authentication authentication =
                new UsernamePasswordAuthenticationToken("user", "user");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        //sets security context in session.
        MockHttpSession session = setSecurityContextInSession(securityContext);
        
        //attempt to access login page while already logged in
        //act and asserts
		mockMvc.perform(get("/auth/login")
				.session(session)
	            .contentType(MediaType.TEXT_HTML)
	            .accept(MediaType.TEXT_HTML))
	            .andExpect(status().is(302))
	            .andExpect(redirectedUrl(redirectedTo))
	            .andExpect(forwardedUrl(null));
	}
	
	/**
	 * Sets the Security Context in session
	 * @param securityContext
	 * @return MockHttpSession
	 */
	private MockHttpSession setSecurityContextInSession(
			SecurityContext securityContext) {
		MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                securityContext);
        
		return session;
	}
	
}
