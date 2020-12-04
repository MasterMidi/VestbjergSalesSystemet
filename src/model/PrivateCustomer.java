package model;

public class PrivateCustomer extends Person {
	private double balance;
	private String cpr; // int, m�ske?

	public PrivateCustomer(String phoneNr, String email, String name) {
		super(phoneNr, email, name);
		this.balance = 0d;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCpr() {
		return cpr;
	}

}
