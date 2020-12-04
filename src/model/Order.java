package model;

import java.util.Date;

public class Order {
	private int orderNumber;
	private Date date;
	private OrderStatus status;
	private boolean delivery;
	private Date deliveryDate; 
	private String specialInstructions;
	private PaymentMethod payment;
}
