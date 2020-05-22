package com.examples.hw430;

import java.io.Serializable;

public class Ballplayer implements Serializable {

	private static final long serialVersionUID = -991350506099795883L;
	private String name;
	private float ba;
	private int hrs;
	
	public Ballplayer(String name, float ba, int hrs) {
		super();
		this.name = name;
		this.ba = ba;
		this.hrs = hrs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBa() {
		return ba;
	}

	public void setBa(float ba) {
		this.ba = ba;
	}

	public int getHrs() {
		return hrs;
	}

	public void setHrs(int hrs) {
		this.hrs = hrs;
	}

	@Override
	public String toString() {
		return "Ballplayer [name=" + name + ", ba=" + ba + ", hrs=" + hrs + "]";
	}
	
	
	
}
