package com.yaksha.assignment;

public class Customer {

	private String customerId;
	private String name;

	// Getters and Setters
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ToString method for easy printing
	@Override
	public String toString() {
		return "Customer{id='" + customerId + "', name='" + name + "'}";
	}
}
