package test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.CustomerController;
import model.Person;
import model.customer.BuisnessCustomer;
import model.customer.CustomerContainer;
import model.customer.PrivateCustomer;

class CustomerControllerTest {

	CustomerController controller;
	CustomerContainer container;

	@BeforeEach
	void setUp() throws Exception {
		controller = new CustomerController();
		container = CustomerContainer.getInstance();
		container.prepareForTest();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() {
		// 2 customers
		controller.createBuisnessCustomer("Buisness Customer", "mail@mail.com", "12345678", "10150817", "12345678",
				"Ike med M");
		controller.createPrivateCustomer("Navn", "email@gmail.com", "87654321", "0609001234");
		assertEquals(2, container.amountOfCustomers());
	}

	@Test
	void testGet() {
		controller.createBuisnessCustomer("Buisness Customer", "mail@mail.com", "12345678", "10150817", "12345678",
				"Ike med M");
		
		Person customerFromController = controller.getCustomer("12345678");
		List<Person> customerFromContainer = container.findCustomers("12345678");
		
		if(customerFromContainer == null || customerFromController == null)
			fail("either controller or container customer is null!");
		
		
		int size = customerFromContainer.size();
		if(size != 1)
			fail("CustomersFromContainer should be 1. Its " + size + " now.");
		
		assertSame(customerFromController,customerFromContainer);
	}
	
	@Test
	void testPrivateCustomerType()
	{

		controller.createPrivateCustomer("Navn", "email@gmail.com", "87654321", "0609001234");
		
		Person privateCustomer = controller.getCustomer("87654321");
		
		assertTrue(privateCustomer instanceof PrivateCustomer);
	}
	
	@Test
	void testBuisnessCustomerType()
	{
		controller.createBuisnessCustomer("Buisness Customer", "mail@mail.com", "12345678", "10150817", "12345678",
				"Ike med M");
		
		Person buisnessCustomer = controller.getCustomer("12345678");
		
		assertTrue(buisnessCustomer instanceof BuisnessCustomer);
	}

}
