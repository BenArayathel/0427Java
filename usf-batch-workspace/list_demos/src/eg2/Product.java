package eg2;

public class Product implements Comparable<Product>{

	private int id;
	private String name;
	private double cost;
	private float rating;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String name, double cost, float rating) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", cost=" + cost + ", rating=" + rating + "]";
	}

	@Override
	public int compareTo(Product o) {
		//System.out.print(this );
	//	System.out.println(o);
		Integer i1=this.id;
		Integer i2=o.id;
		return i2.compareTo(i1);
	}
	
}
