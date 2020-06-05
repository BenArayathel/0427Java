package com.protestorganizer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="protest")
public class Protest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="protest_id")
	private int id;
	
	@Column(name="address_id")
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="address")
	private Address address;
	
	@Column(name="start_date_time")
	private Date startDateTime;
	
	@Column(name="estimated_attendance")
	private int estimatedAttendance;
	
	@Column(name="cause")
	private String cause;
	
	public Protest() {
		super();
	}

	public Protest(int id, Address address, int estimatedAttendance, String cause) {
		super();
		this.id = id;
		this.address = address;
		this.estimatedAttendance = estimatedAttendance;
		this.cause = cause;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public int getEstimatedAttendance() {
		return estimatedAttendance;
	}

	public void setEstimatedAttendance(int estimatedAttendance) {
		this.estimatedAttendance = estimatedAttendance;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		return "Protest [id=" + id + ", addressId=" + address + ", estimatedAttendance=" + estimatedAttendance + ", cause="
				+ cause + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Protest other = (Protest) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
