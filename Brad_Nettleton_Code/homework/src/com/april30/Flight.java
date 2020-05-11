package com.april30;

public class Flight  implements Comparable<Flight> {
	private int fid;
	private String name;
	private String souce;
	private String destination;
	private float rating;
	private double ticketPrice;
	
	public Flight() {
		
	}
	public Flight(int fid, String name, String souce, String destination, float rating, double ticketPrice) {
		super();
		this.fid = fid;
		this.name = name;
		this.souce = souce;
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
	public String getSouce() {
		return souce;
	}
	public void setSouce(String souce) {
		this.souce = souce;
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
	public void setRatings(float rating) {
		this.rating = rating;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	@Override
	public String toString() {
		return "Flight [fid=" + fid + ", name=" + name + ", souce=" + souce + ", destination=" + destination
				+ ", rating=" + rating + ", ticketPrice=" + ticketPrice + "]";
	}

	@Override
	public int compareTo(Flight o) {
		Integer fid1 = this.fid;
		Integer fid2 = o.fid;
		return fid1.compareTo(fid2);
	}
	
	
}
