package com.RevBank;

public class UserData {
	private String cust_id;
	private String fname;
	private String lname;
	private String dob;
	private String email;
	private String minc;
	private String city;
	private String psw;
	private String pho;
	private String withdr;
	private String balance;
	public String getCust_id;
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public String getCust_id() {
		return cust_id;
	}
	public String getWithdr() {
		return withdr;
	}
	public void setWithdr(String withdr) {
		this.withdr = withdr;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMinc() {
		return minc;
	}
	public void setMinc(String minc) {
		this.minc = minc;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getPho() {
		return pho;
	}
	public void setPho(String pho) {
		this.pho = pho;
	}
	@Override
	public String toString() {
		return "UserData [cust_id=" + cust_id + ", fname=" + fname + ", lname=" + lname + ", dob=" + dob + ", email="
				+ email + ", minc=" + minc + ", city=" + city + ", psw=" + psw + ", pho=" + pho + ", withdr=" + withdr
				+ ", balance=" + balance + ", getBalance()=" + getBalance() + ", getCust_id()=" + getCust_id()
				+ ", getWithdr()=" + getWithdr() + ", getFname()=" + getFname() + ", getLname()=" + getLname()
				+ ", getDob()=" + getDob() + ", getEmail()=" + getEmail() + ", getMinc()=" + getMinc() + ", getCity()="
				+ getCity() + ", getPsw()=" + getPsw() + ", getPho()=" + getPho() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	

}