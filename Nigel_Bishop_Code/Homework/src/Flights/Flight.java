package Flights;

public class Flight implements Comparable<Flight>
{
	private int fId;
	private String name;
	private String source;
	private String destination;
	private float rating;
	private double ticketPrice;
	
	public Flight() {
		
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
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

	public Flight(int fId, String name, String source, String destination, float rating, double ticketPrice) {
		super();
		this.fId = fId;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.rating = rating;
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Flight [Flight ID = " + fId + ", Name = " + name + ", Source = " + source + ", Destination = " + destination
				+ ", Rating = " + rating + ", Ticket Price = $" + ticketPrice + "]";
	}

	@Override
	public int compareTo(Flight obj) {
		Integer i1 = this.fId;          // wrapping int to Integer (i1)
		Integer i2 = obj.fId;           // assigning flight object to Integer (i2)
		return i1.compareTo(i2);        // Using  comparable (interface) and compareTo (method) to compare between two Integer to determine the largest 
	}                                   // i1.compareTo(i2) - Ascending Order    |   i2.compareTo(i1)  - Descending Order
	

	


}
