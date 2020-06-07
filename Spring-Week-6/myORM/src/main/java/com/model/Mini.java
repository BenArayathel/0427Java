package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mini_table")
public class Mini {

	@Id
	@Column(name = "mini_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "mini_name", unique = true, nullable = false)
	private String name;

	public Mini() {
		super();
	}

	public Mini(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + "]";
	}

}
