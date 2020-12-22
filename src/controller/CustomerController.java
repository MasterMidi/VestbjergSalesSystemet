package controller;

import java.util.List;

import model.people.BuisnessCustomer;
import model.people.CustomerContainer;
import model.people.DiscountGroup;
import model.people.Person;
import model.people.PrivateCustomer;

public class CustomerController {

	private CustomerContainer customerContainer;

	public CustomerController() {
		customerContainer = CustomerContainer.getInstance();
	}

	public void createPrivateCustomer(String name, String email, String phoneNr, String address, String cpr) {
		createPrivateCustomer(name, email, phoneNr, address, cpr, null);
	}

	/**
	 * 
	 * @param name
	 * @param email
	 * @param phoneNr
	 * @param cpr
	 * @param discountGroup
	 */
	public void createPrivateCustomer(String name, String email, String phoneNr, String address, String cpr,
			DiscountGroup discountGroup) {
		PrivateCustomer customer = new PrivateCustomer(phoneNr, email, name, address, cpr, discountGroup);
		customerContainer.addCustomer(customer);
	}

	public void createBuisnessCustomer(String name, String email, String phoneNr, String address, String cvr,
			String contactNumber, String contactPerson) {
		createBuisnessCustomer(name, email, phoneNr, address, cvr, contactNumber, contactPerson, null);
	}

	public void createBuisnessCustomer(String name, String email, String phoneNr, String address, String cvr,
			String contactNumber, String contactPerson, DiscountGroup discountGroup) {
		BuisnessCustomer customer = new BuisnessCustomer(phoneNr, email, name, address, contactNumber, contactPerson,
				cvr);
		customerContainer.addCustomer(customer);
	}

	public List<Person> findCustomers(String input) {
		return customerContainer.findCustomers(input);
	}

	public Person getDefaultCustomer() {
		return customerContainer.getDefaultCustomer();
	}

	public void setDefaultCustomer(Person defaultCustomer) {
		customerContainer.setDefaultCustomer(defaultCustomer);
		;
	}
}
