package model.product;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.sale.Placement;

public abstract class Product {

	private String name;
	private String barcode;
	private String description;
	private LinkedList<Price> prices;
	private List<Placement> placements;

	public Product(String name, String barcode, String description, Double price) {
		this.prices = new LinkedList<>();
		this.placements = new ArrayList<>();
		this.name = name;
		this.barcode = barcode;
		this.description = description;
		if (price != null) {
			prices.addFirst(new Price(price));
		}
	}

	public void addPrice(double price, String date) {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date newDate = null;
		try {
			newDate = format.parse(date);
		} catch (ParseException e) {
		}
		prices.addFirst(new Price(price,newDate));
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPlacements(List<Placement> placements) {
		this.placements = placements;
	}

	public Product(String name, String barcode, String description) {
		this(name, barcode, description, null);
	}

	public String getName() {
		return name;
	}

	public void addPlacement(Placement placement) {
		placements.add(placement);
	}

	public String getBarcode() {
		return barcode;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice(Date date) {
		return getPriceObj(date).getPrice();

	}

	public Double getPrice() {
		Date currTime = Calendar.getInstance().getTime();
		return getPrice(currTime);

	}
	public Price getPriceObj(Date date) {
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
		return currPrice;
	}
	

	public List<Placement> getPlacements() {
		return new ArrayList<Placement>(placements);
	}

}
