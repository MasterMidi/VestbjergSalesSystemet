package main;

import controller.EmployeeController;
import controller.OrderController;
import controller.ProductController;
import model.PaymentMethod;
import model.PersonRole;
import model.customer.CustomerContainer;
import model.customer.PrivateCustomer;

public class TryMe {
	ProductController prodCon;
	EmployeeController empCon;

	public TryMe() {
		prodCon = new ProductController();
		empCon = new EmployeeController();
		prodCon.createSellableProduct("Magnuses gamle sko", "1111", "De lugter lidt", 200d, 10);
		empCon.createEmployee("10101010", "hej@hejsa.dk", "Mike", 4321, PersonRole.manager);
		CustomerContainer.getInstance()
				.addCustomer(new PrivateCustomer("12345678", "somehting@gmail.com", "My man", "87654321"));
		prodCon.createSellableProduct("Toothbrush", "12345678", "A toothbrush", 20d, 1);
		prodCon.createSellableProduct("Toothpaste", "13579246", "some toothpaste", 40d, 1);
		prodCon.createSellableProduct("a very long string to cut", "98765432", "you know it ;)", 200d, 1);
		prodCon.createSellableProduct("Nails", "89642378", "to nail your m...", 60d, 2);

		OrderController orderController = new OrderController();
		orderController.createOrder();
		orderController.getProduct("12345678");
		orderController.getProduct("13579246");
		orderController.getProduct("98765432");
		orderController.getProduct("89642378", 2);
		orderController.attachCustomer("12345678");
		orderController.finishSale(PaymentMethod.creditcard);
		// new CreateOrderOption().printReceipt(orderController.getCurrentOrder());
	}
}
