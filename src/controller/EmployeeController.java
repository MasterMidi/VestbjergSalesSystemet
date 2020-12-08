package controller;

import model.Employee;
import model.EmployeeContainer;
import model.PersonRole;

public class EmployeeController {

	private EmployeeContainer container;
	private static Employee currentEmployee = new Employee("24267667", "worker@fml.xxx", "José", 34564, PersonRole.clerk);
	
	public EmployeeController()
	{
		container = EmployeeContainer.getInstance();
	}
	
	public Employee createEmployee(String phoneNr, String email, String name, int employeeNumber, PersonRole role) {
		
		Employee employee = new Employee(phoneNr, email, name, employeeNumber, role);
		container.addEmployee(employee);
		
		return employee;
	}	
	
	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public Employee getEmployee(int employeeNumber) {
		return container.getEmployee(employeeNumber);
	}
	
	public void login(int employeeNumber)
	{
		currentEmployee = getEmployee(employeeNumber);
	}
	

}
