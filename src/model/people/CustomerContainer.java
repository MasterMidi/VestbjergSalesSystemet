package model.people;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * Used to find customers matching the input. If input is number it will be assumed to be a phone number, and therefore use it as a key for the hashmap, therefore O(1) acces time.
	 * if searching by name, the access time will be O(n), however this can return multiple people.
	 * @param input input can be a number (as a string) or a name.
	 * @return Returns a list of customers matching the input string
	 */
	public List<Person> findCustomers(String input) {
		List<Person> customerList = new ArrayList<>();

		// checks the input string, to see 
		boolean isName = input.trim().matches("[^\\d]+");

		if (isName) {
			for (Person customer : customers.values()) {
				if (customer.getName().contains(input))
					customerList.add(customer);
			}
		} else {
			Person currPerson = customers.get(input);
			if (currPerson != null)
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