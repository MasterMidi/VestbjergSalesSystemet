package model;

import java.util.LinkedList;

public class OrderContainer {
	private int counter;
	private static OrderContainer instance;
	private LinkedList<Order> orders;

	private OrderContainer() {
		orders = new LinkedList<>();
	}

	public static OrderContainer getInstance() {
		if (instance == null) {
			instance = new OrderContainer();
		}
		return instance;
	}

	public Order getOrder(String orderNumber) {
		Order res = null;
		boolean done = false;
		int index = 0;
		while (!done && index < orders.size()) {
			if (orders.get(index).getOrderNumber().equals(orderNumber)) {
				res = orders.get(index);
				done = true;
			}
		}
		return res;
	}

	public void addOrder(Order order) {
		String orderTemplate = "0000000000";
		String orderCurrentNumber = Integer.toString(counter++);
		String orderNumber = orderTemplate.substring(0, orderTemplate.length() - orderCurrentNumber.length() + 1)
				+ orderCurrentNumber;
		order.setOrderNumber(orderNumber);
		orders.addFirst(order);
	}
	
	public void clear()
	{
		orders.clear();
	}
	
	public int getOrderCount()
	{
		return orders.size();
	}

}
