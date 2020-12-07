package controller;

import model.Employee;
import model.EmployeeContainer;
import model.PersonRole;

public class EmployeeController {

	private EmployeeContainer container;
	
	public EmployeeController()
	{
		container = EmployeeContainer.getInstance();
	}
	
	public Employee createEmployee(String phoneNr, String email, String name, int employeeNumber, PersonRole role) {
		
		Employee employee = new Employee(phoneNr, email, name, employeeNumber, role);
		container.addEmployee(employeeNumber, employee);
		
		return employee;
	}	

	public Employee getEmployee(int employeeNumber) {
		return container.getEmployee(employeeNumber);
	}

}
