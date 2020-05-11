package com.bank;

public class Employee {

	private int idNum;
	private int empPin;
	private String position;
	private transient int ssn;
	
	public Employee() {
		
	}
	
	public Employee(String position, int idNum, int empPin) {
		super();
		this.position = position;
		this.idNum = idNum;
		this.empPin = empPin;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public int getEmpPin() {
		return empPin;
	}

	public void setEmpPin(int empPin) {
		this.empPin = empPin;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "EmployeeController [position=" + position + ", idNum=" + idNum + ", empPin=" + empPin + "]";
	}
	
	
	
}
