package model.product;

import java.util.HashMap;
import java.util.Map;

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
	
	public int getProductCount()
	{
		return products.size();
	}
	
	public void clear()
	{
		products.clear();
	}

}
