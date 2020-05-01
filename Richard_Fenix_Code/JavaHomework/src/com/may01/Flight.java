package com.may01;

public class Flight {
	
	private Integer id;
	private String name;
	private String origin;
	private String destination;
	private Float ratings;
	private Double ticketPrice;
	public Flight(Integer id, String name, String origin, String destination, Float ratings, Double ticketPrice) {
		super();
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.ratings = ratings;
		this.ticketPrice = ticketPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Float getRatings() {
		return ratings;
	}
	public void setRatings(Float ratings) {
		this.ratings = ratings;
	}
	public Double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", name=" + name + ", origin=" + origin + ", destination=" + destination
				+ ", ratings=" + ratings + ", ticketPrice=" + ticketPrice + "]";
	}

	
	
}
