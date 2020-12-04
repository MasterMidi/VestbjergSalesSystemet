package model;

import java.util.HashMap;

public class EmployeeContainer {
	private EmployeeContainer instance;
	private Map<Integer , V> employees;
	
	private EmployeeContainer() {
		
	}
	
	public EmployeeContainer getInstance() {
		if(instance == null){
			instance = new EmployeeContainer();
		}
		return instance;
	}

}
