package com.acss.poc.repository.onuserrepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acss.poc.account.Account;
import com.acss.poc.account.IAccountRepository;
import com.acss.poc.role.RoleRepository;
import com.acss.poc.role.UserRole;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(locations = {"classpath:META-INF/spring/business-config.xml"
									,"classpath:META-INF/spring/spring-security.xml"
									,"classpath:META-INF/spring/spring-securityPOC-servlet.xml"})

public class WhenSaveOrUpdateUser {
	
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
		
	@Test
	public void shouldInsertInDBOnValidRole(){
		String role = "ROLE_NEW";
		String userName = "admin";
		
		UserRole savedRole = roleRepository.save(new UserRole(userName,role));
		assertNotNull(savedRole);
		assertEquals(new Long(4), savedRole.getId());
		UserRole checkRole = roleRepository.findById(4L);
		assertNotNull(checkRole);
		assertEquals(savedRole.getRole(),checkRole.getRole());
		assertEquals("admin", checkRole.getUsername());
	}
	
	@Test
	public void shouldInsertInDbOnValidAccount(){
		//Arrange
		String firstName = "Harry";
		String lastName = "Potter";
		String userName = "harry69";
		String password = "quidditch";
		String email = "harry@hogwarts.com";
		
		Account newAccount = new Account();
		newAccount.setFirstName(firstName);
		newAccount.setLastName(lastName);
		newAccount.setUsername(userName);
		newAccount.setPassword(password);
		newAccount.setEmail(email);
		//act
		accountRepository.save(newAccount);
		
		//assert
		Account newAdded = accountRepository.findByUserName("harry69");
		assertNotNull("User failed to add :",newAdded);
		assertEquals("Added user is not matching with expected",firstName,newAdded.getFirstName());
		assertEquals("Added user is not matching with expected",lastName,newAdded.getLastName());
		assertEquals("Added user is not matching with expected",userName,newAdded.getUsername());
		assertEquals("Added user is not matching with expected",email,newAdded.getEmail());
		
		//Test if the role is saved.
		Set<UserRole> authorities = newAdded.getAuthorities();
		assertEquals("User is not having the appropriate role",1,authorities.size());
		
	}
	
}
