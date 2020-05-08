package model;

public class Stuff {
	
	private int stuff_id;
	private String stuff_name;
	private boolean stuff_iscool;
	private int stuff_amount;
	private String thingName;
	
	public Stuff(int stuff_id, String stuff_name, boolean stuff_iscool, int stuff_amount) {
		super();
		this.stuff_id = stuff_id;
		this.stuff_name = stuff_name;
		this.stuff_iscool = stuff_iscool;
		this.stuff_amount = stuff_amount;
	}

	public Stuff() {
		// TODO Auto-generated constructor stub
	}

	public int getStuff_id() {
		return stuff_id;
	}

	public void setStuff_id(int stuff_id) {
		this.stuff_id = stuff_id;
	}

	public String getStuff_name() {
		return stuff_name;
	}

	public void setStuff_name(String stuff_name) {
		this.stuff_name = stuff_name;
	}

	public boolean isStuff_iscool() {
		return stuff_iscool;
	}

	public void setStuff_iscool(boolean stuff_iscool) {
		this.stuff_iscool = stuff_iscool;
	}

	public int getStuff_amount() {
		return stuff_amount;
	}

	public void setStuff_amount(int stuff_amount) {
		this.stuff_amount = stuff_amount;
	}

	@Override
	public String toString() {
		return "Stuff [stuff_id=" + stuff_id + ", stuff_name=" + stuff_name + ", stuff_iscool=" + stuff_iscool
				+ ", stuff_amount=" + stuff_amount + "]";
	}
	
	
	

}
