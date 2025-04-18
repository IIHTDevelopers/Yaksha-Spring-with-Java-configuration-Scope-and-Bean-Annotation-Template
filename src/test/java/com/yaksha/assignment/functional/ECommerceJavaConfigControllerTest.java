package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yaksha.assignment.Customer;
import com.yaksha.assignment.Order;
import com.yaksha.assignment.Product;
import com.yaksha.assignment.config.AppConfig;
import com.yaksha.assignment.utils.CustomParser;

public class ECommerceJavaConfigControllerTest {

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	// Test if the Spring context loads the beans correctly from Java configuration
	@Test
	public void testApplicationContextLoads() throws IOException {
		try {
			// Load the context using Java-based configuration
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

			// Retrieve beans from the context by name to avoid ambiguity
			Product product = context.getBean("product", Product.class); // Referring to the "product" bean
			Product productPrototype = context.getBean("productPrototype", Product.class); // Referring to the
																							// "productPrototype" bean
			Customer customer = context.getBean(Customer.class);
			Order order = context.getBean(Order.class);

			// Assert that beans are created
			boolean productLoaded = product != null;
			boolean customerLoaded = customer != null;
			boolean orderLoaded = order != null;
			boolean productPrototypeLoaded = productPrototype != null;

			// Log the checks
			System.out.println("Product bean loaded: " + productLoaded);
			System.out.println("Product (Prototype) bean loaded: " + productPrototypeLoaded);
			System.out.println("Customer bean loaded: " + customerLoaded);
			System.out.println("Order bean loaded: " + orderLoaded);

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), productLoaded && customerLoaded && orderLoaded && productPrototypeLoaded,
					businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false,
					businessTestFile);
		}
	}

	// Test if AppConfig class contains @Configuration annotation
	@Test
	public void testAppConfigHasConfiguration() throws IOException {
		String filePath = "src/main/java/com/yaksha/assignment/config/AppConfig.java"; // Path to your AppConfig.java

		try {
			// Check if @Configuration annotation is present at class level
			boolean result = CustomParser.checkClassAnnotation(filePath, "Configuration");

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), result, businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test if Product bean is correctly created with @Bean and @Scope annotations
	@Test
	public void testProductBeanCreation() throws IOException {
		String filePath = "src/main/java/com/yaksha/assignment/config/AppConfig.java"; // Path to your AppConfig.java

		try {
			// Check for @Bean annotation for Product bean
			boolean productBeanCreated = CustomParser.checkMethodAnnotation(filePath, "Bean");

			// Check if the Product bean has @Scope annotation with "prototype"
			boolean productScopeValid = CustomParser.checkMethodAnnotation(filePath, "Scope");

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), productBeanCreated && productScopeValid, businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test to check if 1 Order, 1 Customer, and 2 Product beans are present in the
	// AppConfig
	@Test
	public void testBeansPresenceInAppConfig() throws IOException {
		try {
			// Load the context using Java-based configuration
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

			// Retrieve beans from the context
			Product product = context.getBean("product", Product.class); // Singleton Product bean
			Product productPrototype = context.getBean("productPrototype", Product.class); // Prototype Product bean
			Customer customer = context.getBean(Customer.class);
			Order order = context.getBean(Order.class);

			// Assert the presence of beans
			boolean productLoaded = product != null;
			boolean productPrototypeLoaded = productPrototype != null;
			boolean customerLoaded = customer != null;
			boolean orderLoaded = order != null;

			// Verify that exactly one instance of each Product bean exists
			boolean onlyOneProductBean = product != null && productPrototype != null
					&& !product.equals(productPrototype);

			// Log the checks for debugging
			System.out.println("Product bean loaded: " + productLoaded);
			System.out.println("Product Prototype bean loaded: " + productPrototypeLoaded);
			System.out.println("Customer bean loaded: " + customerLoaded);
			System.out.println("Order bean loaded: " + orderLoaded);
			System.out.println("Only one Product bean and one ProductPrototype bean present: " + onlyOneProductBean);

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(),
					productLoaded && productPrototypeLoaded && customerLoaded && orderLoaded && onlyOneProductBean,
					businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false,
					businessTestFile);
		}
	}

	// Test to check if 2 Product beans are present with correct scope values
	// (singleton and prototype)
	@Test
	public void testProductBeansScope() throws IOException {
		try {
			// Load the context using Java-based configuration
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

			// Retrieve beans from the context by name to avoid ambiguity
			Product productSingleton = context.getBean("product", Product.class); // Singleton Product bean
			Product productPrototype = context.getBean("productPrototype", Product.class); // Prototype Product bean

			// Assert the presence of beans
			boolean productSingletonLoaded = productSingleton != null;
			boolean productPrototypeLoaded = productPrototype != null;

			// Verify that exactly one instance of each Product bean exists
			boolean onlyOneProductBean = productSingleton != null && productPrototype != null
					&& !productSingleton.equals(productPrototype);

			// Verify the scope of the Product beans (Singleton vs Prototype)
			boolean productSingletonScopeCorrect = context.getBeanFactory().getBeanDefinition("product").getScope()
					.equals("singleton");
			boolean productPrototypeScopeCorrect = context.getBeanFactory().getBeanDefinition("productPrototype")
					.getScope()
					.equals("prototype");

			// Log the checks for debugging
			System.out.println("Singleton Product bean loaded: " + productSingletonLoaded);
			System.out.println("Prototype Product bean loaded: " + productPrototypeLoaded);
			System.out.println("Singleton Scope is correct: " + productSingletonScopeCorrect);
			System.out.println("Prototype Scope is correct: " + productPrototypeScopeCorrect);
			System.out.println("Both Product beans are distinct: " + onlyOneProductBean);

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), productSingletonLoaded && productPrototypeLoaded && onlyOneProductBean
					&& productSingletonScopeCorrect && productPrototypeScopeCorrect, businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false,
					businessTestFile);
		}
	}
}
