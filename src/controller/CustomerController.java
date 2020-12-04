package controller;

import model.PrivateCustomer;
import model.customer.CustomerContainer;

public class CustomerController {
	
	CustomerContainer customerContainer;
	
	public CustomerController()
	{
		//customerContainer = CustomerContainer.getInstance();
	}
	
	public boolean create(String name, String email, String phoneNr, String cpr)
	{
		
		//Customer customer = new Customer(name,email,phoneNr,cpr,0);
		//return customerContainer.addCustomer(customer); <- boolean
		
		return true; // <- to be deleted!;
	}
	
	public boolean create(String name, String email, String phoneNr, String cvr,String contactNumber, String contactPerson)
	{
		
		//BuisnessCustomer customer = new Customer(name,email,phoneNr,cvr,contactNumber, contactPerson);
		//return customerContainer.addCustomer(customer); <- boolean
		
		return true;
	}
	
	public PrivateCustomer getPrivateCustomer(String search)
	{
		return new PrivateCustomer();
	}
	
	public boolean update()
	{
		return true;
	}
	
	public boolean delete()
	{
		return true;
	}
	
	
	
}
