package com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//imports from javax instead of hibernate, because they are more generic
//and we could switch to different tools in the future with ease

@Entity // this is something to persist to the db
@Table(name="painting_table") // specifically it is a table, and we name it, then use the name the bean
public class Painting {
	
	//first just make the basic model, then go back and fill in the spring annotations
	//this is where the all hibernate stuff lives
	//for a simple project like this, we just need to let hibernate know
	// 1. what the entity/table is (the class)
	// 2. what the columns are
	// 3. specifics about the columns, like the names and
	// useful tools like generating ids
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
	@Column(name="painting_title", nullable=false, unique=true)
	public String title;
	
	@Column(name="painting_medium", nullable=false)
	public String medium;
	
	//constructors
	public Painting() {
		super();
	}
	
	public Painting(int id, String title, String medium) {
		super();
		this.id = id;
		this.title = title;
		this.medium = medium;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}

	//to string
	@Override
	public String toString() {
		return "Painting [id=" + id + ", title=" + title + ", medium=" + medium + "]";
	}
	
	
	

}
