package com.yaksha.assignment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yaksha.assignment.config.AppConfig;

public class ConfigurationScopeBeanApp {

	public static void main(String[] args) {
		// Load the Spring context using Java-based configuration
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// Retrieve beans from the context
		Product product = context.getBean("product", Product.class);
		Product productPrototype = context.getBean("productPrototype", Product.class);
		Customer customer = context.getBean(Customer.class);
		Order order = context.getBean(Order.class);

		// Display product and customer details
		System.out.println("Product (Singleton scope): " + product);
		System.out.println("Product (Prototype scope): " + productPrototype);
		System.out.println("Customer: " + customer);
		System.out.println("Order: " + order);

		// Demonstrate bean lifecycle (PostConstruct/PreDestroy) - Optionally, add
		// @PostConstruct and @PreDestroy annotations in the beans
		context.close();
	}
}
