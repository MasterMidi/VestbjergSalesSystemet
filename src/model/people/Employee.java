package model.people;

public class Employee extends Person {
	private int employeeNumber;

	public Employee(String phoneNr, String email, String name, int employeeNumber, PersonRole role) {
		super(phoneNr, email, name, role);
		this.employeeNumber = employeeNumber;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

}
