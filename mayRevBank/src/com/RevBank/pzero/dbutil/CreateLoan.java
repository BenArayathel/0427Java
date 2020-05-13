package com.RevBank.pzero.dbutil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.RevBank.UserData;


  public class CreateLoan {
	  
	  private String userid;
		private String fname;
		private String lname;
		private String address;
		private String empS;
		private String minc;
		private String ssn;
		private String psw;
		private String pho;
	  public CreateLoan(String userid, String fname, String lname, String address, String empS, String minc,
				String ssn, String psw, String pho) {
			super();
			this.userid = userid;
			this.fname = fname;
			this.lname = lname;
			this.address = address;
			this.empS = empS;
			this.minc = minc;
			this.ssn = ssn;
			this.psw = psw;
			this.pho = pho;
		}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmpS() {
		return empS;
	}
	public void setEmpS(String empS) {
		this.empS = empS;
	}
	public String getMinc() {
		return minc;
	}
	public void setMinc(String minc) {
		this.minc = minc;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
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
	
  
  
  
  
  
  
  
  
  
  
  
  
  
  }
 