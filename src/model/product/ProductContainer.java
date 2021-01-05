package model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductContainer {
	private static ProductContainer instance;
	private Map<String, Product> products;

	private ProductContainer() {
		products = new HashMap<>();
	}

	public static ProductContainer getInstance() {
		if (instance == null) {
			instance = new ProductContainer();
		}
		return instance;
	}

	public Product getProduct(String barcode) {
		return products.get(barcode);
	}

	public void addProduct(Product product) {
		products.put(product.getBarcode(), product);
	}

	public int getProductCount() {
		return products.size();
	}

	public void clear() {
		products.clear();
	}

	public List<Product> getProducts(String input) {
		List<Product> productList = null;

		boolean isName = input.trim().matches("[^\\d]+");

		if (input == null || input.isBlank()) {
			productList = new ArrayList<>(products.values());
		} else {
			productList = products.values().stream()
					.filter(product -> (isName) ? product.getName().toLowerCase().contains(input.toLowerCase())
							: product.getBarcode().contains(input))
					.collect(Collectors.toList());
		}

		return productList;
	}

	public void editProduct(String currBarcode, String name, String barcode, String description, double price, String date,
			int amount) {
		Product currProduct = this.getProduct(currBarcode);
		products.remove(currBarcode);
		currProduct.setName(name);
		currProduct.setDescription(description);
		currProduct.setBarcode(barcode);
		currProduct.addPrice(price, date);
		((SellableProduct)currProduct).setStock(amount);
		this.addProduct(currProduct);

	}

	public void setProductStatus(String barcode, ProductStatus status) {
		Product currProduct = this.getProduct(barcode);
		((SellableProduct)currProduct).setStatus(status);
		
	}
}
