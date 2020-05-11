package com.hackbank.business.services.atype;

import java.util.List;

import com.hackbank.persistence.dao.atype.AccountTypeDAO;
import com.hackbank.persistence.dao.atype.AccountTypeDAOImpl;
import com.hackbank.persistence.models.AccountType;

public class AccountTypeServiceImpl implements AccountTypeService{

	@Override
	public List<AccountType> getAllAccountType() {
		AccountTypeDAO aType = new AccountTypeDAOImpl();
		return aType.getAllAccountType();
	}

}
