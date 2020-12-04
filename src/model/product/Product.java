package model.product;

public abstract class Product {

	private String name;
	private String barcode;
	private String description;
	private Price price;

	public Product(String name, String barcode, String description, Price price) {
		super();
		this.name = name;
		this.barcode = barcode;
		this.description = description;
		this.price = price;
	}

	public Product(String name, String barcode, String description) {
		this(name, barcode, description, null);
	}

	public String getName() {
		return name;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price.getPrice();
	}

}
