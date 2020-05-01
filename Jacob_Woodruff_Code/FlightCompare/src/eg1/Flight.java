package eg1;

public class Flight implements Comparable<Flight>{
	
	private int fid;
	private String name;
	private String source;
	private String destination;
	private double ticketPrice;
	
	public Flight() {
		
	}

	public Flight(int fid, String name, String source, String destination, double ticketPrice) {
		super();
		this.fid = fid;
		this.name = name;
		this.source = source;
		this.destination = destination;
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

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Flight [fid=" + fid + ", name=" + name + ", source=" + source + ", destination=" + destination
				+ ", ticketPrice=" + ticketPrice + "]";
	}

	@Override
	public int compareTo(Flight o) {
		Integer f1 = this.fid;
		Integer f2 = o.fid;
		
		return f1.compareTo(f2);
	}
	

}
