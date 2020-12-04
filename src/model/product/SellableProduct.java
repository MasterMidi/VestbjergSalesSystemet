package model.product;

import model.WarehouseStatus;

public class SellableProduct extends Product {

	private int warehouseAmount;
	private WarehouseStatus status;

	public SellableProduct(String name, String barcode, String description, int warehouseAmount) {
		super(name, barcode, description);
		this.warehouseAmount = warehouseAmount;
		if (warehouseAmount > 0) {
			this.status = WarehouseStatus.Instock;
		} else {
			this.status = WarehouseStatus.OutOfStock;
		}

	}

	public int getStock() {
		return this.warehouseAmount;
	}

	public WarehouseStatus getStatus() {
		return this.status;
	}
}
