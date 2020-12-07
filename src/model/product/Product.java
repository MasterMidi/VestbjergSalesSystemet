package model.product;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public abstract class Product {

	private String name;
	private String barcode;
	private String description;
	private LinkedList<Price> prices;

	public Product(String name, String barcode, String description, Double price) {
		super();
		this.prices = new LinkedList<>();
		this.name = name;
		this.barcode = barcode;
		this.description = description;
		if (price != null) {
			prices.addFirst(new Price(price));
		}
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

	public Double getPrice(Date date) {
		Price currPrice = null;
		int index = 0;
		boolean found = false;
		while (!found && index < prices.size()) {
			currPrice = prices.get(index);
			if (currPrice.getStartDate().before(date)) {
				found = true;
			}
			index++;
		}
		return currPrice.getPrice();

	}

	public Double getPrice() {
		Date currTime = Calendar.getInstance().getTime();
		return getPrice(currTime);

	}

}
