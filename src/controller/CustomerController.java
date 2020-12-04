package controller;

import model.customer.BuisnessCustomer;
import model.customer.CustomerContainer;
import model.customer.PrivateCustomer;

public class CustomerController {
	
	CustomerContainer customerContainer;
	
	public CustomerController()
	{
		//customerContainer = CustomerContainer.getInstance();
	}
	
	public boolean createPrivateCustomer(String name, String email, String phoneNr, String cpr)
	{
		
		PrivateCustomer customer = new PrivateCustomer(phoneNr, email, name,cpr);
		//return customerContainer.addCustomer(customer); <- boolean
		
		return true; // <- to be deleted!;
	}
	
	public boolean createBuisnessCustomer(String name, String email, String phoneNr, String cvr,String contactNumber, String contactPerson)
	{

		BuisnessCustomer customer = new BuisnessCustomer(phoneNr, email, name, contactNumber, contactPerson, cvr);
		//return customerContainer.addCustomer(customer); <- boolean
		
		return true;
	}
	
	public PrivateCustomer getPrivateCustomer(String phoneNr)
	{
		
		
		return null;
	}
	
	public BuisnessCustomer getBuisnessCustomer(String phoneNr)
	{
		
		
		return null;
	}
	
	public boolean updateBuisnessCustomer()
	{
		return true;
	}
	
	public boolean deleteBuisnessCustomer(String phoneNr)
	{
		//getBuisnessCustomer(phoneNr).role = PersonRole.inactiveClient;
		return true;
	}
	
	public boolean deletePrivateCustomer(String phoneNr)
	{
		//getBuisnessCustomer(phoneNr).role = PersonRole.inactiveClient;
		return true;
	}
	
	
	
}
