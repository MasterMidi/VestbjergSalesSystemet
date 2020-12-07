package test;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.EmployeeController;
import model.Employee;
import model.EmployeeContainer;
import model.PersonRole;

class EmployeeControllerTest {

	EmployeeController controller;
	EmployeeContainer container;

	@BeforeEach
	void setUp() throws Exception {
		controller = new EmployeeController();
		container = EmployeeContainer.getInstance();
		container.prepareForTest();
	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void testCreate() {
		controller.createEmployee("12345678", "mail1@mail", "manager", 12341, PersonRole.manager);
		controller.createEmployee("87654321", "mail2@mail", "ekspedient", 12342, PersonRole.clerk);
		controller.createEmployee("38562857", "mail3@mail", "lager", 12343, PersonRole.warehouse);
		
		assertEquals(3,container.amountOfEmployees());
	}
	
	@Test
	void testGet() {
		controller.createEmployee("12345678", "mail1@mail", "manager", 12345, PersonRole.manager);
		
		Employee controllerEmployee = controller.getEmployee(12345);
		Employee containerEmployee = container.getEmployee(12345);
		
		assertSame(controllerEmployee,containerEmployee);
	}

}
