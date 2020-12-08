package tui.option;

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

		if (textinput.promptBoolean("Print the receipt?")) {
			printReceipt(orderCon.getCurrentOrder());
		}
	}

	public void printReceipt(Order order) {
		System.out.println("*********************************");
		System.out.println(String.format("* %s\t: %s\t*", "Ordernumber", order.getOrderNumber()));
		System.out.println("*-------------------------------*");
		for (OrderLine line : order.getOrderLineList()) {
			System.out.println(String.format("* %s: %s x %s\t*", formatString(line.getProduct().getName(), 11),
					line.getAmount(), line.getPrice() + " DKK"));
		}
		System.out.println("*-------------------------------*");
		System.out.println(String.format("* Total price\t: %s\t*", order.getTotal() + " DKK"));
		System.out.println("*********************************");
	}
	
	private String formatString(String input, int length) {
		try {
			input = input.substring(0, length) + "...";
		} catch(Exception e) {
			int tabs = -1;
			if(input.length() < 6) {
				tabs = 2;
			} else {
				tabs = 1;
			}
			for(int i = 0; i < tabs; i++) {
				input += "\t";
			}
		}
		
		return input;
	}

	private void addProductToOrderline(SellableProduct currProduct) {
		orderCon.getCurrentOrder().addOrderLine(currProduct);
		SellableProduct prod = orderCon.getCurrentOrder().getOrderLineList().get(0).getProduct();
		System.out.println(prod.getName());
	}

	private void scanProducts(TextInput textinput) {
		boolean done = false;
		SellableProduct currProduct = null;
		while (!done) {
			String barcode = textinput.promptString("Enter Barcode [0 to stop]: ");
			currProduct = orderCon.getProduct(barcode);

			if (currProduct != null) {
				addProductToOrderline(currProduct);
			}
			if (barcode.equals("0")) {
				done = true;
			}
		}
	}

}
