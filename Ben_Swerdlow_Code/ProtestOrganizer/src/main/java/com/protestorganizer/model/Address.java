package com.protestorganizer.model;

public class Address {
	
	private int id;
	private int streetNumber;
	private String buildingName;
	private String streetNumberSuffix;
	private String streetName;
	private String streetType;
	private String streetDirection;
	private String addressType;
	private String addressTypeIdentifier;
	private String localMunicipality;
	private String city;
	private String governingDistrict;
	private String postalArea;
	private String country;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(/*int id, */int streetNumber, String buildingName, String streetNumberSuffix, String streetName,
			String streetType, String streetDirection, String addressType, String addressTypeIdentifier,
			String localMunicipality, String city, String governingDistrict, String postalArea, String country) {
		super();
//		this.id = id;
		this.streetNumber = streetNumber;
		this.buildingName = buildingName;
		this.streetNumberSuffix = streetNumberSuffix;
		this.streetName = streetName;
		this.streetType = streetType;
		this.streetDirection = streetDirection;
		this.addressType = addressType;
		this.addressTypeIdentifier = addressTypeIdentifier;
		this.localMunicipality = localMunicipality;
		this.city = city;
		this.governingDistrict = governingDistrict;
		this.postalArea = postalArea;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getStreetNumberSuffix() {
		return streetNumberSuffix;
	}

	public void setStreetNumberSuffix(String streetNumberSuffix) {
		this.streetNumberSuffix = streetNumberSuffix;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getStreetDirection() {
		return streetDirection;
	}

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressTypeIdentifier() {
		return addressTypeIdentifier;
	}

	public void setAddressTypeIdentifier(String addressTypeIdentifier) {
		this.addressTypeIdentifier = addressTypeIdentifier;
	}

	public String getLocalMunicipality() {
		return localMunicipality;
	}

	public void setLocalMunicipality(String localMunicipality) {
		this.localMunicipality = localMunicipality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGoverningDistrict() {
		return governingDistrict;
	}

	public void setGoverningDistrict(String governingDistrict) {
		this.governingDistrict = governingDistrict;
	}

	public String getPostalArea() {
		return postalArea;
	}

	public void setPostalArea(String postalArea) {
		this.postalArea = postalArea;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [streetNumber=" + streetNumber + ", buildingName=" + buildingName + ", streetNumberSuffix="
				+ streetNumberSuffix + ", streetName=" + streetName + ", streetType=" + streetType
				+ ", streetDirection=" + streetDirection + ", addressType=" + addressType + ", addressTypeIdentifier="
				+ addressTypeIdentifier + ", localMunicipality=" + localMunicipality + ", city=" + city
				+ ", governingDistrict=" + governingDistrict + ", postalArea=" + postalArea + ", country=" + country
				+ "]";
	}

}
