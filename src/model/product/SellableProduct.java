package model.product;

import model.WarehouseStatus;

public class SellableProduct extends Product {

	private int warehouseAmount;
	private WarehouseStatus status;

	public SellableProduct(String name, String barcode, String description, int warehouseAmount,
			WarehouseStatus status) {
		super(name, barcode, description);
		this.warehouseAmount = warehouseAmount;
		this.status = status;
	}

	public int getStock() {
		return this.warehouseAmount;
	}

	public WarehouseStatus getStatus() {
		return this.status;
	}
}
