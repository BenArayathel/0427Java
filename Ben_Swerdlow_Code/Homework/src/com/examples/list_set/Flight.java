package com.examples.list_set;

public class Flight implements Comparable<Flight> {
	
	private int fid;
	private String name;
	private String source;
	private String destination;
	private float rating;
	double ticketPrice;
	
	public Flight() {
		super();
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
	public int compareTo(Flight flight) {
		Integer fid1 = this.getFid();
		Integer fid2 = flight.getFid();
		return fid1.compareTo(fid2);
	}

	@Override
	public String toString() {
		return "Flight [fid=" + fid + ", name=" + name + ", source=" + source + ", destination=" + destination
				+ ", rating=" + rating + ", ticketPrice=" + ticketPrice + "]";
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
	
	

}
/*
 * Flight(int fid, String name, String source, String destination, float rating, double ticketPrice) 
 * 
 * default sorting fid using List, then customize as you want
 * 
 * also find out about difference between LinkedList and PriorityQueue, the two classes implementing the Queue Interface
 * 
 * Additionally: now use Set Interface instead of List interface
 * 
 * Sidenote: when you are interacting with a new datastructure or making your own, figure out CRUD first (Create Read Update Delete)
 * 
 * Additional assignment: 
 * 		make your own object other than flight and serialize it and deserialize it
 * 		serialize one of the data structure interfaces (Set, List, Queue) to be comfortable with storing multiple objects
 */