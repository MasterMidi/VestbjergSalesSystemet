package controller;

import model.Person;
import model.customer.BuisnessCustomer;
import model.customer.CustomerContainer;
import model.customer.PrivateCustomer;

public class CustomerController {

	CustomerContainer customerContainer;

	public CustomerController() {
		customerContainer = CustomerContainer.getInstance();
	}

	public void createPrivateCustomer(String name, String email, String phoneNr, String cpr) {

		PrivateCustomer customer = new PrivateCustomer(phoneNr, email, name, cpr);
		customerContainer.addCustomer(customer);

	}

	public void createBuisnessCustomer(String name, String email, String phoneNr, String cvr, String contactNumber,
			String contactPerson) {

		BuisnessCustomer customer = new BuisnessCustomer(phoneNr, email, name, contactNumber, contactPerson, cvr);
		customerContainer.addCustomer(customer);
	}

	public Person getCustomer(String phoneNr) {
		return customerContainer.getCustomer(phoneNr);
	}

}
