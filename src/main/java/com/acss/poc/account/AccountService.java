package com.acss.poc.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
	
	private IAccountRepository accountRepository;
	
	@Autowired
	public AccountService(IAccountRepository accountRepository){
		this.accountRepository = accountRepository;
	}
	
	public Account findByUsername(String username) {
		return accountRepository.findByUserName(username);
	}

	public Account saveOrUpdate(Account account) {
		//if user is existing, retrive the unchanged information like id and password.
		if(!account.isNew()){
			Account existingUser = accountRepository.findByUserName(account.getUsername());
			account.setPassword(existingUser.getPassword());
		}
		//if not then proceed with saving.
		return accountRepository.save(account);
	}

}
