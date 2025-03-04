package com.yaksha.assignment;

public class Product {

	private String name;
	private double price;

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// ToString method for easy printing
	@Override
	public String toString() {
		return "Product{name='" + name + "', price=" + price + "}";
	}
}
