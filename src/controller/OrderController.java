package controller;

import java.util.Calendar;
import java.util.List;

import model.Order;
import model.OrderContainer;
import model.OrderStatus;
import model.PaymentMethod;
import model.Person;
import model.product.SellableProduct;

public class OrderController {
	private OrderContainer orderContainer;
	private ProductController productController;
	private Order order;

	public OrderController() {
		orderContainer = OrderContainer.getInstance();
		productController = new ProductController();
	}

	public void createOrder() {
		order = new Order();
		order.setEmployee(new EmployeeController().getCurrentEmployee());
	}

	public SellableProduct getProduct(String barcode) {
		return productController.getProduct(barcode);
	}

	public SellableProduct scanProduct(String barcode) {
		SellableProduct currentProduct = getProduct(barcode);
		if (currentProduct != null) {
			addProductToOrderLine(currentProduct);
		}
		return currentProduct;
	}

	public void addProductToOrderLine(SellableProduct sellable) {
		order.addOrderLine(sellable);
	}

	public void editProductPrice(int index, double price) {
		order.editProductPrice(index, price);
	}
	
	public void editProductAmount(int index, int amount) {
		order.editProductAmount(index, amount);
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
