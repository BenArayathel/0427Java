package com.pzero.v1.business.services.atype;

import java.util.List;

import com.pzero.v1.persistence.dao.atype.AccountTypeDAO;
import com.pzero.v1.persistence.dao.atype.AccountTypeDAOImpl;
import com.pzero.v1.persistence.models.AccountType;

public class AccountTypeServiceImpl implements AccountTypeService{

	@Override
	public List<AccountType> getAllAccountType() {
		AccountTypeDAO aType = new AccountTypeDAOImpl();
		return aType.getAllAccountType();
	}

}
