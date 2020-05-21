package com.basic.models;

public class CryptoCurrency {
	private String name;
	private String symbol;
	private String currentPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "CryptoCurrency [name=" + name + ", symbol=" + symbol + ", currentPrice=" + currentPrice + "]";
	}
}
