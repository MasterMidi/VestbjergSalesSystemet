package model.people;

public class PrivateCustomer extends Person {
	private double balance;
	private String CPR;
	private DiscountGroup discountGroup;

	public PrivateCustomer(String phoneNr, String email, String name, String address, String CPR,
			DiscountGroup discountGroup) {
		super(phoneNr, email, name, address, PersonRole.activeCustomer);
		this.CPR = CPR;
		this.balance = 0d;
		this.discountGroup = discountGroup;
	}

	public PrivateCustomer(String phoneNr, String email, String name, String address, String CPR) {
		this(phoneNr, email, name, address, CPR, null);
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

	public DiscountGroup getDiscountGroup() {
		return discountGroup;
	}

	public void setDiscountGroup(DiscountGroup discountGroup) {
		this.discountGroup = discountGroup;
	}

}
