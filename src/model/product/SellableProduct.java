package model.product;

public class SellableProduct extends Product {

	private int warehouseAmount;
	private WarehouseStatus status;

	public SellableProduct(String name, String barcode, String description, Double price, int warehouseAmount) {
		super(name, barcode, description, price);
		this.warehouseAmount = warehouseAmount;
		if (warehouseAmount > 0) {
			this.status = WarehouseStatus.Instock;
		} else {
			this.status = WarehouseStatus.OutOfStock;
		}

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
}
