package test;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ProductController;
import model.product.Product;
import model.product.ProductContainer;

class ProductControllerTest {

	ProductController controller;
	ProductContainer container;
	
	@BeforeEach
	void setUp() throws Exception {
		controller = new ProductController();
		container = ProductContainer.getInstance();
		container.clear();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() {
		controller.createSellableProdct("Name", "12345", "This is a description", 3);
		controller.createSellableProdct("Name2", "54321", "This is a description, but for another product", 2);
		
		assertEquals(2,container.getProductCount());
	}
	
	
	@Test
	void testGet() {
		controller.createSellableProdct("Name", "12345", "This is a description", 3);
		
		Product controllerProduct = controller.getProduct("12345");
		Product containerProduct = container.getProduct("12345");
		
		if(controllerProduct == null || containerProduct == null)
			fail("either controller or container product is null!");
		
		assertSame(controllerProduct,containerProduct);
	}

}
