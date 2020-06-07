package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "empvo")
public class EmployeeVO  {

	// had : implements Serializable
	//private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "empid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(min = 3, max = 20)
	@NotNull
	@Column(name = "fname")
	private String firstName;

	@Size(min = 3, max = 20)
	@Column(name = "lname")
	private String lastName;

	@Pattern(regexp = ".+@.+\\.[a-z]+")
	@Column(name = "email")
	private String email;

	public EmployeeVO() {
		super();
	}

	public EmployeeVO(Integer id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeeVO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}

}
