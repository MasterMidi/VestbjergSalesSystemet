package model;

import java.util.List;
import java.util.LinkedList;

public class OrderContainer {
	
	private static OrderContainer instance; 
	private LinkedList<Order> orders;
	
	private OrderContainer() {
		orders = new LinkedList<>();
	}
	
	public static OrderContainer getInstance() {
		if(instance == null){
			instance = new OrderContainer();
		}
		return instance;
	}
	
	public Order getOrder(int orderNumber) {
		Order res = null;
		boolean done = false;
		int i = 0;
		while(!done && i < orders.size()) {
			if(orders.get(i).getOrderNumber() == orderNumber) {
				res = orders.get(i);
				done = true;
			}
		}
		return  res;
	}
	
	public void addOrder(Order order) {
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
