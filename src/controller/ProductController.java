package controller;

import model.product.Product;
import model.product.ProductContainer;

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

}
