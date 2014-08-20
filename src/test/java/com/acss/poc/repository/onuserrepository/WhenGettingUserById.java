package com.acss.poc.repository.onuserrepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acss.poc.account.Account;
import com.acss.poc.account.IAccountRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(locations = {"classpath:META-INF/spring/business-config.xml"
									,"classpath:META-INF/spring/spring-security.xml"})
public class WhenGettingUserById {
	
	@Autowired
	private IAccountRepository accountRepository;
		
	@Test
	public void shouldReturnAccountOnExistingUser(){
		//Arrange
		final String admin = "admin";
		final String username = "admin";
		final String firstName = "Gail";
		
		//Act
		Account account = accountRepository.findByUserName(admin);
		
		//assert
		assertNotNull(account);
		assertEquals("Retrieved userName is not mathing with expected",username, account.getUsername());
		assertEquals("Retrieved FistName is not mathing with expected",firstName, account.getFirstName());
		
	}
	
	//assert if it will throw this exception.
	@Test(expected=EmptyResultDataAccessException.class)
	public void shouldThrowExceptionOnNonExistingUser(){
		//arrange
		final String nonExistingUser = "casper";
		//act
		accountRepository.findByUserName(nonExistingUser);
	}
	
}
