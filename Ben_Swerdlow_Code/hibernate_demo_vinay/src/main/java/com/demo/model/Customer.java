package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int cid;
	private String name;
	@OneToOne(fetch = FetchType.LAZY);// Will not make it eager. Will only pull laptop details when specifically asked for it
	private Laptop laptop;
	
	public Customer() {
		super();
	}
	
	public Customer(String name, Laptop laptop) {
		super();
		this.name = name;
		this.laptop = laptop;
	}
	
	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Laptop getLaptop() {
		return laptop;
	}
	
	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
	
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", name=" + name + ", laptop=" + laptop + "]";
	}
	
	

}
