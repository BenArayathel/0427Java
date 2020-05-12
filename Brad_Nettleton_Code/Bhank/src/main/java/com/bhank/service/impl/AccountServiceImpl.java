package com.bhank.service.impl;

import java.util.List;

import com.bhank.dao.impl.AccountDAOImpl;
import com.bhank.exception.BusinessException;
import com.bhank.model.Account;
import com.bhank.model.Customer;
import com.bhank.service.AccountService;

public class AccountServiceImpl implements AccountService {

	AccountDAOImpl dao = new AccountDAOImpl();
	
	@Override
	public Account createAccount(Account account) throws BusinessException {
		if(account==null) {
			throw new BusinessException("Account object was not created");
		} else if(account.getBalance()<0) {
			throw new BusinessException("Starting balance is less than 0");
		} else {
			account = dao.createAccount(account);
		}
		return account;
	}

	@Override
	public Account deposit(Account account, double amount) throws BusinessException {
		if(account==null) {
			throw new BusinessException("Account object was not created");
		} else if (!isValidDeposit(amount)) {
			throw new BusinessException("Deposit amount cannot be less than or equal to zero");
		} else {
			account = dao.deposit(account, amount);
		}
		return account;
	}

	private boolean isValidDeposit(double amount) {
		boolean b = false;
		if(amount<=0) {
			b=true;
		}
		return b;
	}

	@Override
	public Account withdraw(Account account, double amount) throws BusinessException {
		if(account==null) {
			throw new BusinessException("Account object was not created");
		} else if (!isValidWithdrawal(account, amount)) {
			throw new BusinessException("Withdraw amount exceeds account balance");
		} else {
			account = dao.withdraw(account, amount);
		}
		return account;
	}

	private boolean isValidWithdrawal(Account account, double amount) {
		boolean b = false;
		if(amount<account.getBalance()) {
			b=true;
		}
		return b;
	}

	@Override
	public Account postMoneyTransfer(Account account, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account acceptMoneyTransfer(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccountsByCustomer(Customer customer) throws BusinessException {
		List<Account> accounts = dao.selectAllAccountsByCustomer(customer);
		return accounts;
	}

	@Override
	public Account selectAccountByNameAndPassword(String name, String Password) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selectAccountById(String id) throws BusinessException {
		Account account = new Account();
		if(id==null) {
			throw new BusinessException("id is null");
		} else if(!isValidId(id)) {
			throw new BusinessException("id is invalid");
		} else {
			account = dao.selectAccountById(id);
		}
		return account;
	}

	private boolean isValidId(String id) {
		boolean b=false;
		if(id.matches("ACCU[a-zA-Z]{2}[0-9]{5}")) {
			b=true;
		}
		return b;
	}

	@Override
	public void deleteAccount(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account approveAccount(Account account) throws BusinessException {
		if(account==null) {
			throw new BusinessException("Account does not exist");
		} else if (!account.isPending()) {
			System.out.println("Account is not pending");
		} else if(account.isApproved()) {
			throw new BusinessException("Account is already approved");
		} else {
			account = dao.approveAccount(account);
		}
		return account;
	}

	@Override
	public Account declineAccount(Account account) throws BusinessException {
		if(account==null) {
			throw new BusinessException("Account does not exist");
		} else if (!account.isPending()) {
			throw new BusinessException("Account is not pending");
		} else if(account.isApproved()) {
			throw new BusinessException("Cannot decline approved account");
		} else {
			account = selectAccountById(account.getId());
			account = dao.approveAccount(account);
		}
		return account;
	}

}
