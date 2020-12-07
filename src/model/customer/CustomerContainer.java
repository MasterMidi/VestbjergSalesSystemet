package model.customer;

import java.util.HashMap;
import java.util.Map;

import model.Person;

public class CustomerContainer {
	private static CustomerContainer instance;
	private Map<String, Person> customers;

	private CustomerContainer() {
		customers = new HashMap<>();
	}

	public static CustomerContainer getInstance() {
		if (instance == null) {
			instance = new CustomerContainer();
		}
		return instance;
	}

	public Person getCustomer(String phoneNumber) {
		return customers.get(phoneNumber);
	}

	public void addCustomer(Person customer) {
		customers.put(customer.getPhoneNr(), customer);
	}

}