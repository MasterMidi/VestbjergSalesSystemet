package model;

public class Employee extends Person {
	private int employeeNumber;
	private EmployeeRole role;

	public Employee(String phoneNr, String email, String name, int employeeNumber, EmployeeRole role) {
		super(phoneNr, email, name);
		this.employeeNumber = employeeNumber;
		this.role = role;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public EmployeeRole getRole() {
		return role;
	}

}
