package com.revature.collections;

public class Flight implements Comparable<Flight>{
//	(int fid, String name, String source, String destination, float ratings, double ticketPrice
	private int fid;
	private String name;
	private String source;
	private String destination;
	private float rating;
	private double ticketPrice;
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}
	
	public Flight(int fid, String name, String source, String destination, float rating, double ticketPrice) {
		super();
		this.fid = fid;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.rating = rating;
		this.ticketPrice = ticketPrice;
	}
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fid;
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
		Flight other = (Flight) obj;
		if (fid != other.fid)
			return false;
		return true;
	}

	@Override
	public int compareTo(Flight f) {
		Integer f1 = this.fid;
		Integer f2 = f.getFid();
		return f1.compareTo(f2);
	}

	@Override
	public String toString() {
		return "Flight [fid=" + fid + ", name=" + name + ", source=" + source + ", destination=" + destination
				+ ", rating=" + rating + ", ticketPrice=" + ticketPrice + "]";
	}
	
	
	
}
