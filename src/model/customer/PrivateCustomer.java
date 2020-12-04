package model.customer;

import model.Person;
import model.PersonRole;

public class PrivateCustomer extends Person {
	private double balance;
	private String CPR; // int, m�ske?

	public PrivateCustomer(String phoneNr, String email, String name, String CPR) {
		super(phoneNr, email, name, PersonRole.activeCustomer);
		this.CPR = CPR;
		this.balance = 0d;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCPR() {
		return CPR;
	}

}
