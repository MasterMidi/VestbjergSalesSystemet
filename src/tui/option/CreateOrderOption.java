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
		System.out.println("******************************");
		System.out.println(String.format("* %s: %s   *", "Ordernumber", order.getOrderNumber()));
		for (OrderLine line : order.getOrderLineList()) {
			System.out.println(String.format("* %s: %s x %s *", line.getProduct().getName().substring(0, 16),
					line.getAmount(), line.getPrice()));
		}
		System.out.println("******************************");
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
