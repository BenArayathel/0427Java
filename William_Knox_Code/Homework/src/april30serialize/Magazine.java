package april30serialize;

import java.io.Serializable;

public class Magazine implements Comparable<Magazine>, Serializable {
	private transient int darts;
	private int capacity;
	private static final long serialVersionUID = 5416854321L;
	
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

	@Override
	public String toString() {
		return "Magazine [capacity=" + capacity + "]";
	}
	
	@Override
	public int compareTo(Magazine m) {
		return ((Integer) this.capacity).compareTo((Integer) m.getCapacity());
	}
}
