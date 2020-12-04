package model.customer;

import model.Person;

public class BuisnessCustomer extends Person {
	private String contact;
	private String contactPhone;
	private String CVR;
	private double balance;

	public BuisnessCustomer(String phoneNr, String email, String name, String contact, String contactPhone,
			String cVRNumber) {
		super(phoneNr, email, name);
		this.contact = contact;
		this.contactPhone = contactPhone;
		CVR = cVRNumber;
		this.balance = 0d;
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

}
