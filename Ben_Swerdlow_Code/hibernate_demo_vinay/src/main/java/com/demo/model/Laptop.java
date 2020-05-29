package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Laptop {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int lid;
	private String companyName;
	private double cost;
	
	public Laptop() {
		super();
	}
	
	public int getLid() {
		return lid;
	}
	
	public void setLid(int lid) {
		this.lid = lid;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Laptop(String companyName, double cost) {
		super();
		this.companyName = companyName;
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "Laptop [lid=" + lid + ", companyName=" + companyName + ", cost=" + cost + "]";
	}
	
	
}
