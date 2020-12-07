package model.product;

import java.util.Calendar;
import java.util.Date;

public class Price {
	private double price;
	private Date startDate;

	public double getPrice() {
		return price;
	}

	public Price(Double price, Date startDate) {
		super();
		this.price = price;
		this.startDate = startDate;
	}

	public Price(Double price) {
		this(price, Calendar.getInstance().getTime());

	}

	public Date getStartDate() {
		return startDate;
	}

}
