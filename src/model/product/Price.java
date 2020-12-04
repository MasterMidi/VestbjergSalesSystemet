package model.product;

import java.util.Date;

public class Price {
	private double price;
	private Date startDate;

	public double getPrice() {
		return price;
	}

	public Price(double price, Date startDate) {
		super();
		this.price = price;
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

}
