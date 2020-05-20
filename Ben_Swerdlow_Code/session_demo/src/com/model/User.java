package com.model;

public class User {
	
	private int userId;
	private String fname;
	private String lname;
	private String email;
	private long contact;
	private String job;
	private String role;
	private String org;
	private String graduation;
	private double score;
	private String major;
	private int year;
	
	
	public User() {
		super();
	}


	public User(int userId, String fname, String lname, String email, long contact, String job, String role, String org,
			String graduation, double score, String major, int year) {
		super();
		this.userId = fname.hashCode()+lname.hashCode();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.contact = contact;
		this.job = job;
		this.role = role;
		this.org = org;
		this.graduation = graduation;
		this.score = score;
		this.major = major;
		this.year = year;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getContact() {
		return contact;
	}


	public void setContact(long contact) {
		this.contact = contact;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getOrg() {
		return org;
	}


	public void setOrg(String org) {
		this.org = org;
	}


	public String getGraduation() {
		return graduation;
	}


	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", contact="
				+ contact + ", job=" + job + ", role=" + role + ", org=" + org + ", graduation=" + graduation
				+ ", score=" + score + ", major=" + major + ", year=" + year + ", getUserId()=" + getUserId()
				+ ", getFname()=" + getFname() + ", getLname()=" + getLname() + ", getEmail()=" + getEmail()
				+ ", getContact()=" + getContact() + ", getJob()=" + getJob() + ", getRole()=" + getRole()
				+ ", getOrg()=" + getOrg() + ", getGraduation()=" + getGraduation() + ", getScore()=" + getScore()
				+ ", getMajor()=" + getMajor() + ", getYear()=" + getYear() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	

}
