package model.product;

import java.util.ArrayList;
import java.util.List;

public class SellableProduct extends Product {

	private int warehouseAmount;
	private ProductStatus status;
	private List<SellableProduct> products;

	public SellableProduct(String name, String barcode, String description, Double price, int warehouseAmount) {
		super(name, barcode, description, price);
		this.warehouseAmount = warehouseAmount;
		if (warehouseAmount > 0) {
			this.status = ProductStatus.Instock;
		} else {
			this.status = ProductStatus.OutOfStock;
		}
		this.products = new ArrayList<>();
	}

	public SellableProduct(String name, String barcode, String description, int warehouseAmount) {
		this(name, barcode, description, null, warehouseAmount);
	}

	public int getStock() {
		return this.warehouseAmount;
	}

	public ProductStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	
	public List<SellableProduct> getProducts(){
		return new ArrayList<>(products);
	}
	
	public void addProduct(SellableProduct product) {
		if(product != null) {
			products.add(product);
		} 
	}
	public void setStock(int i) {
		warehouseAmount = i;
	}
}
