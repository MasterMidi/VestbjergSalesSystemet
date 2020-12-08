package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.OrderController;
import model.OrderContainer;

class OrderControllerTest {

	OrderController controller;
	OrderContainer container;
	
	@BeforeEach
	void setUp() throws Exception {
		controller = new OrderController();
		container = OrderContainer.getInstance();
		container.clear();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
	}

}
