package model;

public abstract class Person {

	private String phoneNr;
	private String email;
	private String name;

	public Person(String phoneNr, String email, String name) {
		super();
		this.phoneNr = phoneNr;
		this.email = email;
		this.name = name;
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

}