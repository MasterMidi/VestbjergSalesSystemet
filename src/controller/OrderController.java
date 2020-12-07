package controller;

import java.util.Calendar;
import java.util.List;

import model.Order;
import model.OrderContainer;
import model.OrderStatus;
import model.PaymentMethod;
import model.Person;
import model.product.Product;
import model.product.ProductContainer;
import model.product.SellableProduct;

public class OrderController {
	private OrderContainer orderContainer;
	private Order order;

	public OrderController() {
		orderContainer = OrderContainer.getInstance();
	}

	public void createOrder() {
		order = new Order();
		order.setEmployee(new EmployeeController().getCurrentEmployee());
	}

	public SellableProduct getProduct(String barcode) {
		Product product = ProductContainer.getInstance().getProduct(barcode);
		SellableProduct sellable = null;
		if (product instanceof SellableProduct) {
			sellable = (SellableProduct) product;
			order.addOrderLine(sellable);
		}
		return sellable;
	}

	public void editProductPrice(int index, double price) {
		order.editProductPrice(index, price);
	}

	public List<Person> findCustomers(String phone) {
		return new CustomerController().findCustomers(phone);
	}

	public void attachCustomer(String phone) {
		Person customer = findCustomers(phone).get(0);
		order.setCustomer(customer);
	}

	public void finishSale(PaymentMethod payment) {
		order.setDate(Calendar.getInstance().getTime());
		order.setStatus(OrderStatus.completed);
		order.setDelivery(false);
		order.setPayment(payment);
		orderContainer.addOrder(order);
	}

	public Order getCurrentOrder() {
		return order;
	}
}
