package com.animal.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Animal {
	
	@Id
	@GeneratedValue//(strategy = GenerationType.)
	private int id;
	private String name;
	private int age;
	private String avatar;
	
	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Constructor with fields is not necessary. Will use getters and settes with an empty one
//	public Animal(int id, String name, int age, String avatar) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.age = age;
//		this.avatar = avatar;
//	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", age=" + age + ", avatar=" + avatar + "]";
	}

}
