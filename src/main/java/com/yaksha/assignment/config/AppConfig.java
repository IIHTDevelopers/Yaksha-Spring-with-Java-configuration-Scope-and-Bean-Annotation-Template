package com.yaksha.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.yaksha.assignment.Customer;
import com.yaksha.assignment.Order;
import com.yaksha.assignment.Product;

@Configuration
public class AppConfig {

    // Defining a singleton-scoped Product bean
    @Bean
    @Scope("singleton")  // Singleton scope is default, but it's specified here for clarity
    public Product product() {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1000.0);
        return product;
    }

    // Defining a prototype-scoped Product bean
    @Bean
    @Scope("prototype")
    public Product productPrototype() {
        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(500.0);
        return product;
    }

    // Defining a Customer bean
    @Bean
    public Customer customer() {
        Customer customer = new Customer();
        customer.setCustomerId("CUST001");
        customer.setName("John Doe");
        return customer;
    }

    // Defining an Order bean
    @Bean
    public Order order() {
        Order order = new Order();
        order.setOrderId("ORD12345");
        order.setTotalAmount(0.0);
        return order;
    }
}
