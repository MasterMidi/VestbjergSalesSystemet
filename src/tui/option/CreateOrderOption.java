package tui.option;

import java.util.List;

import controller.OrderController;
import model.Order;
import model.Order.OrderLine;
import model.Person;
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
		Person currPerson = null;
		boolean done = false;
		while (!done) {
			currPerson = findCustomers();
			if (currPerson != null) {
				done = true;
			}
		}
		attachCustomer(currPerson.getPhoneNr());
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

	private Person findCustomers() {
		TextInput textInput = new TextInput();
		String costumerPhone = textInput.promptString("Find cusomer by phone [0000 is cash customer]");
		List<Person> customers = orderCon.findCustomers(costumerPhone);
		Person currPerson = new TextChoice<Person>("*********************************", true,
				new ListRenderer<Person>() {
					@Override
					public String display(Person option) {
						return String.format("%s - %s - %s", option.getName(), option.getPhoneNr(), option.getEmail());
					}

				}, customers).promptMenu("choose customer ");
		return currPerson;
	}

	public void attachCustomer(String phone) {
		orderCon.attachCustomer(phone);
	}
}
