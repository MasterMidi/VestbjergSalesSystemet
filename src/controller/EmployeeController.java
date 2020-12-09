package controller;

import model.Employee;
import model.EmployeeContainer;
import model.PersonRole;

public class EmployeeController {

	private EmployeeContainer employeeContainer;
	private static Employee currentEmployee = new Employee("24267667", "worker@fml.xxx", "José", 34564,
			PersonRole.clerk);

	public EmployeeController() {
		employeeContainer = EmployeeContainer.getInstance();
	}

	public Employee createEmployee(String phoneNr, String email, String name, int employeeNumber, PersonRole role) {

		Employee employee = new Employee(phoneNr, email, name, employeeNumber, role);
		employeeContainer.addEmployee(employee);

		return employee;
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public Employee getEmployee(int employeeNumber) {
		return employeeContainer.getEmployee(employeeNumber);
	}

	public void login(int employeeNumber) {
		currentEmployee = getEmployee(employeeNumber);
	}

}
