package com.test;

public class BestPractices {
	
	private int numberI;
	
	public BestPractices() {
		super();
		this.numberI = 0;
	}
	
	public static void main(String[] args) {
		final BestPractices bestPrac = new BestPractices();
		System.out.println(bestPrac.numberI);
		bestPrac.otherMethod();
	}
	
	public void otherMethod() {
		System.out.println("Hello");
	}

	public int getNumberI() {
		return numberI;
	}

	public void setNumberI(final int numberI) {
		this.numberI = numberI;
	}

}
