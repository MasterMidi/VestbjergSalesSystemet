package tui.option;

import java.util.List;

import controller.OrderController;
import model.Order;
import model.Order.OrderLine;
import model.product.SellableProduct;
import textinput.TextInput;
import tui.Option;

public class CreateOrderOption extends Option {

	OrderController orderCon;

	public CreateOrderOption() {
		super("Create Order");
		orderCon = new OrderController();
	}

	@Override
	// Mock data for create order.
	public void start() {
		TextInput textinput = new TextInput();
		orderCon.createOrder();
		System.out.println("****** " + getDescription() + "******");
		// TODO Implement function.
		System.out.println("Scan products now - ");
		scanProducts(textinput);
		if (textinput.promptBoolean("Would you like to change the price of any the products you scanned?")) {
			editProductPrice(orderCon.getCurrentOrder());
		}
		if (textinput.promptBoolean("Print the receipt?")) {
			printReceipt(orderCon.getCurrentOrder());
		}
	}

	public void printReceipt(Order order) {
		System.out.println("******************************");
		System.out.println(String.format("* %s: %s   *", "Ordernumber", order.getOrderNumber()));
		for (OrderLine line : order.getOrderLineList()) {
			System.out.println(String.format("* %s: %s x %s *", line.getProduct().getName().substring(0, 16),
					line.getAmount(), line.getPrice()));
		}
		System.out.println("******************************");
	}

	private void scanProducts(TextInput textinput) {
		boolean done = false;
		SellableProduct currProduct = null;
		while (!done) {
			String barcode = textinput.promptString("Enter Barcode [0 to stop]: ");
			currProduct = orderCon.scanProduct(barcode);
			if (currProduct != null) {
				System.out.println("Scanned: " + currProduct);
			}
			if (barcode.equals("0")) {
				done = true;
			}
		}
	}

	private void editProductPrice(Order order) {
		int index = 1;
		List<OrderLine> orderLineList = order.getOrderLineList();
		boolean done = false;
		TextInput textinput = new TextInput();
		System.out.println("****** " + "Edit OrderLines " + "******");
		for (OrderLine currentOrderLine : orderLineList) {
			System.out.println(String.format("(" + index++ + ")" + "%s: %s x %s *",
					currentOrderLine.getProduct().getName().substring(0, 16), currentOrderLine.getAmount(),
					currentOrderLine.getPrice()));
		}
		while (!done) {
			Integer choice = textinput.promptInt("Choose product you want to edit price [0 to stop]: ");
			if(choice > 0 && choice <= orderLineList.size()) {
				Integer newPrice = textinput.promptInt("****** " + "choose new price [-1 to cancel] " + "******");
				if (choice == -1) {
					done = true;
				} else {
					orderLineList.get(choice)
				}
			} else if (choice == 0) {
				done = true;
			}

		}
	}
}
