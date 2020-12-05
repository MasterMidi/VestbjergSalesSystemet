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
	
	public Order(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public boolean isDelivery() {
		return delivery;
	}
	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public PaymentMethod getPayment() {
		return payment;
	}
	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}

}
