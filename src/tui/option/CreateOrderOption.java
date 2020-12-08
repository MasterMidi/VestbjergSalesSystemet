package tui.option;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import controller.OrderController;
import model.Order;
import model.Order.OrderLine;
import model.PaymentMethod;
import model.product.SellableProduct;
import textinput.ListRenderer;
import textinput.TextChoice;
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
		if (textinput.promptBoolean("Would you like to change the price of any of the products you scanned?")) {
			editProductPrice(orderCon.getCurrentOrder());
		}
		PaymentMethod paymentMethod = new TextChoice<PaymentMethod>("Payment methods", false,
				new ListRenderer<PaymentMethod>() {
					@Override
					public String display(PaymentMethod option) {
						return option.toString();
					}
				}, new ArrayList<PaymentMethod>(EnumSet.allOf(PaymentMethod.class))).promptMenu("Choose a method");

		orderCon.finishSale(paymentMethod);
		if (textinput.promptBoolean("Print the receipt?")) {
			printReceipt(orderCon.getCurrentOrder());
		}
	}

	public void printReceipt(Order order) {
		System.out.println("*********************************");
		System.out.println(String.format("* %s\t: %s\t*", "Ordernumber", order.getOrderNumber()));
		System.out.println("*-------------------------------*");
		for (OrderLine line : order.getOrderLineList()) {
			System.out.println(String.format("* %s: %s x %.2f,-\t*", formatString(line.getProduct().getName(), 11),
					line.getAmount(), line.getPrice()));
		}
		System.out.println("*-------------------------------*");
		System.out.println(String.format("* Total price\t: %.2f,-\t*", order.getTotal()));
		System.out.println(String.format("* Tax amounts\t: %.2f,-\t*", order.getTotal() * 0.20));
		System.out.println(String.format("* Cashier\t: %s*", formatString(order.getEmployee().getName(), 11)));
		switch (order.getPayment()) {
		case cash:
			System.out.println("* Paid with cash\t\t*");
			break;
		case creditcard:
			System.out.println("* Paid with creditcard\t\t*");
			break;
		case invoice:
			System.out.println(String.format("* Customer\t: %s*", formatString(order.getCustomer().getName(), 11)));
			System.out.println("* Paid over invoice\t\t*");
			break;
		default:
			break;
		}
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(String.format("* Payment date\t: %s*", formatString(df.format(order.getDate()), 11)));
		System.out.println("*********************************");
	}

	private String formatString(String input, int length) {
		try {
			input = input.substring(0, length) + "...";
		} catch (Exception e) {
			int tabs = -1;
			if (input.length() < 6) {
				tabs = 2;
			} else {
				tabs = 1;
			}
			for (int i = 0; i < tabs; i++) {
				input += "\t";
			}
		}

		return input;
	}

	private void scanProducts(TextInput textinput) {
		boolean done = false;
		SellableProduct currProduct = null;
		while (!done) {
			String barcode = textinput.promptString("Enter Barcode [0 to stop]: ");
			currProduct = orderCon.scanProduct(barcode);
			if (currProduct != null) {
				System.out.println("Scanned: " + currProduct.getName());
			}
			if (barcode.equals("0")) {
				done = true;
			}
		}
	}

	private void editProductPrice(Order order) {
		Boolean done = false;
		List<OrderLine> orderLineList = order.getOrderLineList();
		TextInput textInput = new TextInput();
		while (!done) {
			OrderLine currOrderline = new TextChoice<OrderLine>("*********************************", true,
					new ListRenderer<OrderLine>() {
						@Override
						public String display(OrderLine option) {
							return String.format("%s: %s x %s ,-", formatString(option.getProduct().getName(), 11),
									option.getAmount(), option.getPrice());
						}

					}, orderLineList).promptMenu("Choose a product to edit price");
			if (currOrderline == null) {
				done = true;
			} else {
				Integer newPrice = textInput.promptInt("new payment price: ");
				orderCon.editProductPrice(orderLineList.indexOf(currOrderline), newPrice);
			}

		}
	}
	private void 
}
