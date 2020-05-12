package com.hackbank.persistence.models;

public class AccountType {
	
	private byte id;
	private String name;
	
	public AccountType() {}

	public AccountType(byte id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AccountType [id=" + id + ", name=" + name + "]";
	}
	
	

}
