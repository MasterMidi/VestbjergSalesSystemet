package controller;

import java.util.List;

import model.product.Product;
import model.product.ProductContainer;
import model.product.ProductStatus;
import model.product.SellableProduct;

public class ProductController {
	private ProductContainer productContainer;
	private Product currProduct;

	public ProductController() {
		productContainer = ProductContainer.getInstance();
	}

	public void createSellableProduct(String name, String barcode, String description, Double price, int amount) {
		productContainer.addProduct(new SellableProduct(name, barcode, description, price, amount));
	}

	public void createSellableProduct(String name, String barcode, String description, int amount) {
		createSellableProduct(name, barcode, description, null, amount);
	}

	public SellableProduct getProduct(String barcode) {
		currProduct = productContainer.getProduct(barcode);
		SellableProduct sellable = null;
		if (currProduct instanceof SellableProduct) {
			sellable = (SellableProduct) currProduct;
		}
		return sellable;
	}

	public List<Product> getProducts(String barcode) {
		return productContainer.getProducts(barcode);
	}

	public void updateProduct(String productBarcode, String name, String barcode, String description, double price, String date,
			int amount) {
		productContainer.editProduct(productBarcode, name, barcode, description, price, date, amount);
	}

	public void setProductStatus(String barcode, ProductStatus status) {
		productContainer.setProductStatus(barcode, status);
		
	}
}
