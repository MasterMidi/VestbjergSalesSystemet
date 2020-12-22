package model.people;

public abstract class Person {
	private String phoneNr;
	private String email;
	private String name;
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private PersonRole role;
	private DiscountGroup discountGroup;

	public Person(String phoneNr, String email, String name, PersonRole role) {
		this.phoneNr = phoneNr;
		this.email = email;
		this.name = name;
		this.role = role;
	}

	public Person(String phoneNr, String email, String name, String address, PersonRole role) {
		this(phoneNr, email, name, role);
		this.address = address;
	}

	public String getPhoneNr() {
		return this.phoneNr;
	}

	public String getEmail() {
		return this.email;
	}

	public String getName() {
		return this.name;
	}

	public PersonRole getRole() {
		return role;
	}

	public void setRole(PersonRole newRole) {
		this.role = newRole;
	}

	public DiscountGroup getDiscountGroup() {
		return discountGroup;
	}

	public void setDiscountGroup(DiscountGroup discountGroup) {
		this.discountGroup = discountGroup;
	}
}