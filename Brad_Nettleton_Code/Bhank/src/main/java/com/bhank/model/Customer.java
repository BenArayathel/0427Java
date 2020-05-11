package com.bhank.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
	List<Account> accounts;
	
	public Customer() {
		accounts = new ArrayList<>();
	}
}
