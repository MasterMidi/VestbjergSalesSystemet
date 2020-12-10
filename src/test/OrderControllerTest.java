package test;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.EmployeeController;
import controller.OrderController;
import controller.ProductController;
import model.people.PersonRole;
import model.sale.OrderContainer;
import model.sale.PaymentMethod;

class OrderControllerTest {

	OrderController controller;
	OrderContainer container;
	
	@BeforeEach
	void setUp() throws Exception {
		controller = new OrderController();
		container = OrderContainer.getInstance();
		container.clear();
		
		//Creates a temporary employee to login
		EmployeeController employeeController = new EmployeeController();
		employeeController.createEmployee("123", "mail", "name", 1234, PersonRole.manager);
		employeeController.login(1234);
		
		
		ProductController productController = new ProductController();
		productController.createSellableProduct("Magnuses gamle sko", "1234", "de lugter meget", 10d,2);
		productController.createSellableProduct("Kaffe", "12345", "Nem og hurtig energi!", 15d,20);
		productController.createSellableProduct("Energidrik", "12346", "Nem og hurtig energi!", 15d,20);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testProcessSale() {
		
		controller.createOrder();
		controller.scanProduct("1234");
		controller.scanProduct("12345");
		controller.finishSale(PaymentMethod.creditcard);
		
		controller.createOrder();
		controller.scanProduct("12346");
		
		controller.finishSale(PaymentMethod.cash);
		assertEquals(container.getOrderCount(),2);
	}
	
	@Test
	void testEditPrice() {
		controller.createOrder();
		controller.scanProduct("1234");
		controller.scanProduct("12345");
		controller.editProductPrice(0, 100d);
		
		double orderPrice = controller.getCurrentOrder().getOrderLineList().get(0).getPrice();
		System.out.println("Price is: " + orderPrice);
		assertEquals(orderPrice,100d);
	}

}
