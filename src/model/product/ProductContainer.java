package model.product;

import java.util.HashMap;
import java.util.Map;

public class ProductContainer {
	private ProductContainer instance; 
	private Map<String , Product> products;
	
	private ProductContainer() {
		products = new HashMap<>();
	}
	
	public ProductContainer getInstance() {
		if(instance == null){
			instance = new ProductContainer();
		}
		return instance;
	}
	
	public Product getProduct(String barcode) {
		return products.get(barcode);
	}
	
	public void addProduct(String barcode, Product product) {
		products.put(barcode, product);
	}
	

}
