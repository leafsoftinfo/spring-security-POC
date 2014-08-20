package com.acss.poc.account;

public interface IAccountService {
	Account findByUsername(String username);
	Account saveOrUpdate(Account account);
}
