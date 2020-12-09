package model.customer;

import model.DiscountGroup;
import model.Person;
import model.PersonRole;

public class BuisnessCustomer extends Person {
	private String contact;
	private String contactPhone;
	private String CVR;
	private double balance;
	private DiscountGroup discountGroup;

	public BuisnessCustomer(String phoneNr, String email, String name, String contact, String contactPhone,
			String cvrNumber, DiscountGroup discountGroup) {
		super(phoneNr, email, name, PersonRole.activeCustomer);
		this.contact = contact;
		this.contactPhone = contactPhone;
		CVR = cvrNumber;
		this.balance = 0d;
	}
	
	public BuisnessCustomer(String phoneNr, String email, String name, String contact, String contactPhone,
			String cvrNumber) {
		this(phoneNr, email, name, contact, contactPhone,
				cvrNumber, null);
	}

	public double getSaldo() {
		return balance;
	}

	public void setSaldo(double saldo) {
		this.balance = saldo;
	}

	public String getContact() {
		return contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getCVRNumber() {
		return CVR;
	}
	
	public DiscountGroup getDiscountGroup() {
		return discountGroup;
	}

	public void setDiscountGroup(DiscountGroup discountGroup) {
		this.discountGroup = discountGroup;
	}

}
