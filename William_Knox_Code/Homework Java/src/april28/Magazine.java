package april28;

public class Magazine {
	private int darts;
	private int capacity;
	
	public Magazine(int capacity, int darts) {
		this.capacity = capacity;
		this.darts = darts;
	}

	public int getDarts() {
		return darts;
	}
	public void setDarts(int darts) {
		if (darts < 0) {
			this.darts = 0;
		} else if (darts >= this.capacity) {
			this.darts = this.capacity;
		} else {
			this.darts = darts;
		}
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
