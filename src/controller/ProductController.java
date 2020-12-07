package controller;

import model.product.Product;
import model.product.ProductContainer;
import model.product.SellableProduct;

public class ProductController {
	ProductContainer productContainer;
	Product currProduct;

	public ProductController() {
		productContainer = ProductContainer.getInstance();
	}

	public Product getProduct(String barcode) {
		currProduct = productContainer.getProduct(barcode);
		return currProduct;
	}

	public void createSellableProduct(String name, String barcode, String description, double price, int amount) {
		new SellableProduct(name, barcode, description, price, amount);
	}

	public void createSellableProdct(String name, String barcode, String description, int amount) {
		new SellableProduct(name, barcode, description, amount);
	}
}
