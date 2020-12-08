package model.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public List<Person> findCustomers(String phone) {
		List<Person> customerList = new ArrayList<>();
		Person currPerson = customers.get(phone);
		if (currPerson != null) {
			customerList.add(currPerson);
		}
		return customerList;
	}

	public void addCustomer(Person customer) {
		customers.put(customer.getPhoneNr(), customer);
	}

	/**
	 * Used in unit test
	 * 
	 * @return the amount of customers in the map.
	 */
	public int getCustomerCount() {
		return customers.size();
	}

	/**
	 * Clears the customer map, to ensure test results are right
	 */
	public void clear() {
		customers.clear();
	}

}