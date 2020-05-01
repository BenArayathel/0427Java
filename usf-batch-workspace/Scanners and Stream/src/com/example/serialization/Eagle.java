package com.example.serialization;

import java.io.Serializable;

public class Eagle implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 6343636091132612830L;

	private String wingSpanWidth;
	private String beakColor;
	private transient String name;

	public Eagle() {
		super();
	}

	public Eagle(String wingSpanWidth, String beakColor, String name) {
		super();
		this.wingSpanWidth = wingSpanWidth;
		this.beakColor = beakColor;
		this.name = name;
	}

	public String getWingSpanWidth() {
		return wingSpanWidth;
	}

	public void setWingSpanWidth(String wingSpanWidth) {
		this.wingSpanWidth = wingSpanWidth;
	}

	public String getBeakColor() {
		return beakColor;
	}

	public void setBeakColor(String beakColor) {
		this.beakColor = beakColor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Eagle [wingSpanWidth=" + wingSpanWidth + ", beakColor=" + beakColor + ", name=" + name + "]";
	}

}
