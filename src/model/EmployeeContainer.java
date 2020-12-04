package model;

import java.util.HashMap;
import java.util.Map;

public class EmployeeContainer {
	private EmployeeContainer instance; 
	private Map<Integer , Employee> employees;
	
	private EmployeeContainer() {
		employees = new HashMap<>();
	}
	
	public EmployeeContainer getInstance() {
		if(instance == null){
			instance = new EmployeeContainer();
		}
		return instance;
	}
	
	public Employee getEmployee(int kundeNummer) {
		return employees.get(kundeNummer);
	}
	
	public void addEmployee(int kundeNummer, Employee employee) {
		employees.put(kundeNummer, employee);
	}
	
	
}
