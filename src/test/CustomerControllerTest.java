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
		container.clear();
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
		assertEquals(2, container.getCustomerCount());
	}

	@Test
	void testGet() {
		controller.createBuisnessCustomer("Buisness Customer", "mail@mail.com", "12345678", "10150817", "12345678",
				"Ike med M");
		
		List<Person> customerFromController = controller.findCustomers("12345678");
		List<Person> customerFromContainer = container.findCustomers("12345678");
		
		if(customerFromContainer == null || customerFromController == null)
			fail("either controller or container customer is null!");
		
		int sizeController = customerFromController.size();
		if(sizeController != 1)
			fail("CustomersFromContainer should be 1. Its " + sizeController + " now.");
		
		int sizeContainer = customerFromContainer.size();
		if(sizeContainer != 1)
			fail("CustomersFromContainer should be 1. Its " + sizeContainer + " now.");
		
		assertSame(customerFromController.get(0),customerFromContainer.get(0));
	}
	
	@Test
	void testPrivateCustomerType()
	{

		controller.createPrivateCustomer("Navn", "email@gmail.com", "87654321", "0609001234");
		
		List<Person> privateCustomer = controller.findCustomers("87654321");
		
		assertSame(1, privateCustomer.size());
		
		assertTrue(privateCustomer.get(0) instanceof PrivateCustomer);
	}
	
	@Test
	void testBuisnessCustomerType()
	{
		controller.createBuisnessCustomer("Buisness Customer", "mail@mail.com", "12345678", "10150817", "12345678",
				"Ike med M");
		
		List<Person> buisnessCustomer = controller.findCustomers("12345678");
		
		assertSame(1, buisnessCustomer.size());
		
		assertTrue(buisnessCustomer.get(0) instanceof BuisnessCustomer);
	}

}
