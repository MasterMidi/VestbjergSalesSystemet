package model.people;

import java.util.HashMap;
import java.util.Map;

public class EmployeeContainer {
	private static EmployeeContainer instance;
	private Map<Integer, Employee> employees;

	private EmployeeContainer() {
		employees = new HashMap<>();
	}

	public static EmployeeContainer getInstance() {
		if (instance == null) {
			instance = new EmployeeContainer();
		}
		return instance;
	}

	public Employee getEmployee(int employeeNumber) {
		return employees.get(employeeNumber);
	}

	public void addEmployee(Employee employee) {
		employees.put(employee.getEmployeeNumber(), employee);
	}
	
	public int getEmployeeCount()
	{
		return employees.size();
	}
	
	public void clear()
	{
		employees.clear();
	}

}
