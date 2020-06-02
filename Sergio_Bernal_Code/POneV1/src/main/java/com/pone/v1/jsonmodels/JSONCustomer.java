package com.pone.v1.jsonmodels;

import java.util.List;

import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.Person;

public class JSONCustomer {
	
	private Person person;
	private List<Account> accounts;
	
	public JSONCustomer() {
		// TODO Auto-generated constructor stub
	}

	public JSONCustomer(Person person, List<Account> accounts) {
		super();
		this.person = person;
		this.accounts = accounts;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
