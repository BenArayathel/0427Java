package dao;

import exception.BusinessException;
import model.Account;

import java.util.List;

/*
	Persistence (Database) Layer Interface
	Provides unimplemented methods for communication with DB.
 */
public interface DAO {

	void createAccount(Account account) throws BusinessException;

	void updateAccount(Account account) throws BusinessException;

	List<Account> getAllAccounts() throws BusinessException;

	Account getAccount(String name) throws BusinessException;

//	public abstract void deposit(String account, double amount) throws BusinessException;
//
//	public abstract void withdraw(String account, String amount) throws BusinessException;

}
