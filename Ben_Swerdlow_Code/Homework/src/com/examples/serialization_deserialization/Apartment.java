package com.examples.serialization_deserialization;

import java.io.Serializable;

public class Apartment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4705717960435736686L;
	private double size;
	private int numRooms;
	private boolean furnished;
	private double rent;
	private String address;
	private String aptComplexName;
	
	public Apartment() {
		super();
	}

	public Apartment(double size, int numRooms, boolean furnished, double rent, String address, String aptComplexName) {
		super();
		this.size = size;
		this.numRooms = numRooms;
		this.furnished = furnished;
		this.rent = rent;
		this.address = address;
		this.aptComplexName = aptComplexName;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public boolean isFurnished() {
		return furnished;
	}

	public void setFurnished(boolean furnished) {
		this.furnished = furnished;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAptComplexName() {
		return aptComplexName;
	}

	public void setAptComplexName(String aptComplexName) {
		this.aptComplexName = aptComplexName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		Apartment other = (Apartment) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Apartment [size=" + size + ", numRooms=" + numRooms + ", furnished=" + furnished + ", rent=" + rent
				+ ", address=" + address + ", aptComplexName=" + aptComplexName + "]";
	}

}
