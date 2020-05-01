package april30Flight;

import java.util.Comparator;

public class Flight implements Comparable<Flight> {
	
	private int fid;
	private String name;
	private String source;
	private String destination;
	private double ratings;
	private double ticketPrice;
	
	public Flight(int fid, String name, String source, String destination, double ratings, double ticketPrice) {
		this.fid = fid;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.ratings = ratings;
		this.ticketPrice = ticketPrice;
		
	}
	
	public Flight() {
		
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

	public double getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Flight [fid=" + fid + ", name=" + name + ", source=" + source + ", destination=" + destination
				+ ", ratings=" + ratings + ", ticketPrice=" + ticketPrice + "]";
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
		Integer int1 = this.fid;
		Integer int2 = f.fid;
		return int2.compareTo(int1);
		
	}
	
	
	


}
