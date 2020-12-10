package model.people;

import java.util.Date;

public class DiscountGroup {
	private String name;
	private Date conditions;
	private double discount;
	
	public DiscountGroup(String name, Date conditions, double discount)
	{
		this.name = name;
		this.conditions = conditions;
		this.discount = discount;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getConditions() {
		return conditions;
	}

	public void setConditions(Date conditions) {
		this.conditions = conditions;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
