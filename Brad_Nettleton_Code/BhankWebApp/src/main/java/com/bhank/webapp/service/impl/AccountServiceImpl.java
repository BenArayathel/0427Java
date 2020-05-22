package com.bhank.webapp.service.impl;

import java.util.List;

import com.bhank.webapp.dao.impl.AccountDAOImpl;
import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.main.Main;
import com.bhank.webapp.model.Account;
import com.bhank.webapp.service.AccountService;

public class AccountServiceImpl implements AccountService {

	AccountDAOImpl dao = new AccountDAOImpl();

	@Override
	public Account createAccount(Account account) throws BusinessException {
		if (account == null) {
			Main.logger.error("Account service failed to created account because account object was null");
			throw new BusinessException("Account object was not created");
		} else if (account.getBalance() <= 0) {
			Main.logger.error("Account service failed to created account because account starting balance was less than or equal to zero");
			throw new BusinessException("Starting balance is less than 0");
		} else {
			Main.logger.info("Account service successfully created account: "+account);
			account = dao.createAccount(account);
		}
		return account;
	}

	@Override
	public Account deposit(String id, double amount) throws BusinessException {
		Account account = null;
		if (id == null) {
			Main.logger.error("Account service failed to deposit because id was null");
			throw new BusinessException("id is null");
		} else if (!isValidId(id)) {
			Main.logger.error("Account service failed to deposit due to invalid id \""+id+"\"");
			throw new BusinessException("id is invalid");
		} else if (!isValidDeposit(amount)) {
			Main.logger.error("Account service failed to deposit due to invalid amount \""+amount+"\"");
			throw new BusinessException("Deposit amount cannot be less than or equal to zero");
		} else {
			account = selectAccountById(id);
			if(!account.isPending() && !account.isApproved()) {
				throw new BusinessException("Cannot deposit into declined account");
			} else {
			Main.logger.info("Account service successfully deposited "+amount+" into account with id "+id);
			account = dao.deposit(account, amount);
			}
		}
		return account;
	}

	private boolean isValidDeposit(double amount) {
		boolean b = false;
		if (amount >= 0) {
			Main.logger.info("Account service successfully validated deposit amount \""+amount+"\"");
			b = true;
		}
		return b;
	}

	@Override
	public Account withdraw(String id, double amount) throws BusinessException {
		Account account = null;
		if (id == null) {
			Main.logger.error("Account service failed to withdraw because id was null");
			throw new BusinessException("id is null");
		} else if (!isValidId(id)) {
			Main.logger.error("Account service failed to withdraw due to invalid id \""+id+"\"");
			throw new BusinessException("id is invalid");
		} else {
			account = selectAccountById(id);
		}
		if(!account.isPending() && !account.isApproved()) {
			throw new BusinessException("Cannot withdraw from declined account");
		} else if(!isValidWithdrawal(account, amount)) {
			Main.logger.error("Account service failed to withdraw due to invalid amount \""+amount+"\" due to account with balance of \""+account.getBalance()+"\"");
			throw new BusinessException("Withdraw amount exceeds account balance");
		} else {
			Main.logger.info("Account service successfully withdrew \""+amount+"\" from account with id \""+id+"\"");
			account = dao.withdraw(account, amount);
		}
		return account;
	}

	private boolean isValidWithdrawal(Account account, double amount) {
		boolean b = false;
		Main.logger.info("Account service successfully validated withdraw amount \""+amount+"\" from account \""+account+"\"");
		if (amount < account.getBalance()) {
			b = true;
		}
		return b;
	}

	@Override
	public Account postMoneyTransfer(Account fromAccount, Account toAccount, double amount) throws BusinessException {
		
		return null;
	}

	@Override
	public Account acceptMoneyTransfer(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccountsByCustomer(String customerId) throws BusinessException {
		List<Account> allCustomerAccountsList = null;
		if(customerId==null) {
			Main.logger.error("Account service failed to select all accounts by customer id becuase customer id was null");
			throw new BusinessException("Customer id is null");
		} else {
			Main.logger.info("Account service successfully selected all accounts by customer \""+customerId+"\"");
			allCustomerAccountsList = dao.selectAllAccountsByCustomer(customerId);
		}
		return allCustomerAccountsList;
		
	}

	@Override
	public Account selectAccountByNameAndPassword(String name, String Password) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selectAccountById(String id) throws BusinessException {
		Account account = null;
		if (id == null) {
			Main.logger.error("Account service failed to select account by id becuase id was null");
			throw new BusinessException("id is null");
		} else if (!isValidId(id)) {
			Main.logger.error("Account service failed to select account by id due to invalid id \""+id+"\"");
			throw new BusinessException("id is invalid");
		} else {
			Main.logger.info("Account service successfully selected account by id \""+id+"\"");
			account = dao.selectAccountById(id);
		}
		return account;
	}

	private boolean isValidId(String id) {
		boolean b = false;
		if (id.matches("ACCU[a-zA-Z]{2}[0-9]{5}")) {
			Main.logger.info("Account service successfully validated id \""+id+"\"");
			b = true;
		}
		return b;
	}

	@Override
	public void deleteAccount(Account account) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Account approveAccount(Account account) throws BusinessException {
		if (account == null) {
			Main.logger.error("Account service failed to approve account becuase account object was null");
			throw new BusinessException("Account object is null");
		} else if (!account.isPending()) {
			Main.logger.error("Account service failed to approve account becuase account \""+account+"\" is not pending");
			System.out.println("Account is not pending");
		} else if (account.isApproved()) {
			Main.logger.error("Account service failed to approve account becuase account \""+account+"\" is already approved");
			throw new BusinessException("Account is already approved");
		} else {
			Main.logger.info("Account service successfully approved account \""+account+"\"");
			account = dao.approveAccount(account);
		}
		return account;
	}

	@Override
	public Account declineAccount(Account account) throws BusinessException {
		if (account == null) {
			Main.logger.error("Account service failed to decline account becuase account object was null");
			throw new BusinessException("Account object is null");
		} else if (!account.isPending()) {
			Main.logger.error("Account service failed to decline account becuase account \""+account+"\" is not pending");
			throw new BusinessException("Account is not pending");
		} else if (account.isApproved()) {
			Main.logger.error("Account service failed to decline account becuase account \""+account+"\" is already approved");
			throw new BusinessException("Account is already approved");
		} else {
			Main.logger.info("Account service successfully declined account \""+account+"\"");
			account = dao.declineAccount(account);
		}
		return account;
	}

	public List<Account> selectAllPendingAccounts() throws BusinessException {
		return dao.selectAllPendingAccounts();
	}

}
