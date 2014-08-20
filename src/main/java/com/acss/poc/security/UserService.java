package com.acss.poc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acss.poc.account.Account;
import com.acss.poc.account.IAccountService;

/**
 * Custom service which implements the {@link UserDetailsService}
 * @author gvargas.local
 *
 */
@Service
public class UserService implements UserDetailsService {
	
	private IAccountService accountService;
	
	@Autowired
	public UserService(IAccountService accountService){
		this.accountService = accountService;
	}
	
	public Account findByUsername(String username){
		return accountService.findByUsername(username);
	}
	
	
	/**
	 * For Spring Security
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountService.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	private User createUser(Account account) {
		
		return new User(account.getUsername(), account.getPassword(),account.getAuthorities());
	}
}
