package Flight;

public class Flight implements Comparable<Flight> {

	private int fid;
	private String name;
	private String source;
	private String destination;
	float ratings;
	float ticketPrice;
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Flight(int fid, String name, String source, String destination, float ratings, float ticketPrice) {
		super();
		this.fid = fid;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.ratings = ratings;
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
	public float getRatings() {
		return ratings;
	}
	public void setRatings(float ratings) {
		this.ratings = ratings;
	}
	public float getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	@Override
	public String toString() {
		return "Product [fid=" + fid + ", name=" + name + ", source=" + source + ", destination=" + destination +", ratings="+ ratings+",ticket price="+ticketPrice + "]";
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
	public int compareTo(Flight o) {
		//System.out.print(this );
	//	System.out.println(o);
		Integer i1=this.fid;
		Integer i2=o.fid;
		return i1.compareTo(i2);
	}
}
