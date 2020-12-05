package model;

public abstract class Person {

	private String phoneNr;
	private String email;
	private String name;
	private PersonRole role;

	public Person(String phoneNr, String email, String name, PersonRole role) {
		this.phoneNr = phoneNr;
		this.email = email;
		this.name = name;
		this.role = role;
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
}