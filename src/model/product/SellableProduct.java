package model.product;

import java.util.ArrayList;
import java.util.List;

public class SellableProduct extends Product {

	private int warehouseAmount;
	private WarehouseStatus status;
	private List<SellableProduct> products;

	public SellableProduct(String name, String barcode, String description, Double price, int warehouseAmount) {
		super(name, barcode, description, price);
		this.warehouseAmount = warehouseAmount;
		if (warehouseAmount > 0) {
			this.status = WarehouseStatus.Instock;
		} else {
			this.status = WarehouseStatus.OutOfStock;
		}
		this.products = new ArrayList<>();
	}

	public SellableProduct(String name, String barcode, String description, int warehouseAmount) {
		this(name, barcode, description, null, warehouseAmount);
	}

	public int getStock() {
		return this.warehouseAmount;
	}

	public WarehouseStatus getStatus() {
		return this.status;
	}
	
	public List<SellableProduct> getProducts(){
		return new ArrayList<>(products);
	}
	
	public void addProduct(SellableProduct product) {
		if(product != null) {
			products.add(product);
		} 
	}
}
