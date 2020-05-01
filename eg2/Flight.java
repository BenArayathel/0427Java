package eg2;

public class Flight implements Comparable<Flight>
{
	
	private int flightID;
	private String flightName;
	private String flightSource;
	private String flightDestination;
	private float flightRating;
	private double flightTicketPrice;
	
	public Flight(int flightID, String flightName, String flightSource, String flightDestination, float flightRating, double flightTicketPrice)	{
		super();
		this.flightID = flightID;
		this.flightName = flightName;
		this.flightSource = flightSource;
		this.flightDestination = flightDestination;
		this.flightRating = flightRating;
		this.flightTicketPrice = flightTicketPrice;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + flightID + ", name=" + flightName + ", source=" + flightSource + ", destination=" + flightDestination + ", rating=" + flightRating + ", ticket price=" + flightTicketPrice + "]";
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
		if (flightID != other.flightID)
			return false;
			return true;
	}

	@Override
	public int compareTo(Flight o) {
		//System.out.print(this );
	//	System.out.println(o);
		Integer i1=this.flightID;
		Integer i2=o.flightID;
		return i2.compareTo(i1);
	}
}
