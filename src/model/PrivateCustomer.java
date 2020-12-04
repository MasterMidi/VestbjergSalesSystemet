package model;

public class PrivateCustomer extends Person {
	private double balance;
	private String cpr; // int, m�ske?

	public PrivateCustomer(String phoneNr, String email, String name) {
		super(phoneNr, email, name);

	}
}
