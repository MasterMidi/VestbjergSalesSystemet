package controller;

import java.util.List;

import model.DiscountGroup;
import model.Person;
import model.customer.BuisnessCustomer;
import model.customer.CustomerContainer;
import model.customer.PrivateCustomer;

public class CustomerController {

	private CustomerContainer customerContainer;

	public CustomerController() {
		customerContainer = CustomerContainer.getInstance();
	}

	public void createPrivateCustomer(String name, String email, String phoneNr, String cpr) {
		createPrivateCustomer(name,email,phoneNr,cpr,null);
	}
	
	public void createPrivateCustomer(String name, String email, String phoneNr, String cpr, DiscountGroup discountGroup) {

		PrivateCustomer customer = new PrivateCustomer(phoneNr, email, name, cpr,discountGroup);
		customerContainer.addCustomer(customer);
	}

	public void createBuisnessCustomer(String name, String email, String phoneNr, String cvr, String contactNumber,
			String contactPerson) {
		createBuisnessCustomer(name,email,phoneNr,cvr,contactNumber, contactPerson, null);
	}
	
	public void createBuisnessCustomer(String name, String email, String phoneNr, String cvr, String contactNumber,
			String contactPerson, DiscountGroup discountGroup) {

		BuisnessCustomer customer = new BuisnessCustomer(phoneNr, email, name, contactNumber, contactPerson, cvr);
		customerContainer.addCustomer(customer);
	}

	public List<Person> findCustomers(String input) {
		return customerContainer.findCustomers(input);
	}

}
