package com.yaksha.assignment;

public class Order {

	private String orderId;
	private double totalAmount;

	// Getters and Setters
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	// ToString method for easy printing
	@Override
	public String toString() {
		return "Order{orderId='" + orderId + "', totalAmount=" + totalAmount + "}";
	}
}
