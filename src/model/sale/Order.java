package model.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.people.BuisnessCustomer;
import model.people.Employee;
import model.people.Person;
import model.people.PrivateCustomer;
import model.product.SellableProduct;

public class Order {
	public class OrderLine {
		private SellableProduct product;
		private int amount;
		private double price;

		public OrderLine(SellableProduct product) {
			this(product, 1);
		}

		public OrderLine(SellableProduct product, int amount) {
			this.amount = amount;
			if (product != null) {
				this.product = product;
				this.price = product.getPrice();
			}
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public void addAmount(int amount) {
			this.amount += amount;
		}

		public double getPrice() {
			return price;
		}

		public void editPrice(double price) {
			this.price = price;
		}

		public double getTotal() {
			return amount * price;
		}

		public SellableProduct getProduct() {
			return product;
		}
	}

	private String orderNumber;
	private boolean delivery;
	private Date date;
	private Date deliveryDate;
	private String specialInstructions;
	private Employee employee;
	private Person customer;
	private OrderStatus status;
	private PaymentMethod payment;
	private List<OrderLine> orderLineList;

	public Order() {
		this.orderNumber = null;
		orderLineList = new ArrayList<>();
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	public List<OrderLine> getOrderLineList() {
		return new ArrayList<>(orderLineList);
	}

	private int orderLineContainsProduct(SellableProduct product) {
		int index = -1;
		Iterator<OrderLine> it = orderLineList.iterator();

		for (int i = 0; index == -1 && it.hasNext(); i++) {
			if (it.next().getProduct() == product) {
				index = i;
			}
		}

		return index;
	}

	public void addOrderLine(SellableProduct product, int amount) {
		if (product != null) {
			int index = orderLineContainsProduct(product);
			if (index != -1) {
				orderLineList.get(index).addAmount(amount);
			} else {
				OrderLine ol = new OrderLine(product, amount);
				orderLineList.add(ol);
			}
		}
	}

	public void addOrderLine(SellableProduct product) {
		addOrderLine(product, 1);
	}

	public void editProductPrice(int index, double price) {
		orderLineList.get(index).editPrice(price);
	}

	public void editProductAmount(int index, int amount) {
		orderLineList.get(index).setAmount(amount);
	}

	public double getTotal(boolean useDiscount) {
		int total = 0;
		double discountAmount = 1;

		for (OrderLine line : orderLineList) {
			total += line.getTotal();
		}

		if (useDiscount) {
			if (customer instanceof BuisnessCustomer) {
				BuisnessCustomer _customer = (BuisnessCustomer) customer;

				if (_customer.getDiscountGroup() != null) {
					discountAmount = 1 - _customer.getDiscountGroup().getDiscount();
				}
			} else {
				PrivateCustomer _customer = (PrivateCustomer) customer;

				if (_customer.getDiscountGroup() != null) {
					discountAmount = 1 -_customer.getDiscountGroup().getDiscount();
				}
			}
		}

		return total * discountAmount;
	}
}
