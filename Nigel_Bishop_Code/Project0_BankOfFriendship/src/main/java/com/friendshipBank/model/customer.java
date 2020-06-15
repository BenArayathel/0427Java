package com.friendshipBank.model;

import java.util.Date;

public class customer {
	
	    private String customerID;
	    private String lastName;
	    private String firstName;
	    private String emailAddress;
	    private long contactNumber;
	    private Date dob;
	    private String street;
	    private String city;
	    private String state;

	    public customer() 
	    {
		}

		public String getCustomerID() {
			return customerID;
		}

		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public long getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(long contactNumber) {
			this.contactNumber = contactNumber;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return "customer [Customer ID = " + customerID + ", Last Name = " + lastName + ", First Name = " + firstName
					+ ", Email Address = " + emailAddress + ", Contact Number = " + contactNumber + ", Date Of Birth (DOB) = " + dob
					+ ", Street = " + street + ", City = " + city + ", State = " + state + "]";

		}
	    
	    
	    
	
	
	
}
