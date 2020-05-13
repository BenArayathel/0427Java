package com.RevBank;

public abstract class Application {
	protected String title;
	public abstract void run(String[] args) throws Exception;

	public String getTitle() {
		return title;
	}
}
