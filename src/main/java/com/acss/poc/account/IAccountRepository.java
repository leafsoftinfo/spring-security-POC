package com.acss.poc.account;

import java.util.List;

import org.springframework.dao.DataAccessResourceFailureException;

/**
 * Interface for Account Repository
 * @author gvargas.local
 *
 */
public interface IAccountRepository {
	
	Account findByUserName(String username) throws DataAccessResourceFailureException;
	Account save(Account account);
	boolean removeAccount(Account account);
	boolean updateAccount(Account account);
	List<Account> query(AccountSpecification specification);
}
