package tui.option;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import controller.OrderController;
import model.people.Person;
import model.product.SellableProduct;
import model.sale.Order;
import model.sale.Order.OrderLine;
import model.sale.PaymentMethod;
import textinput.IListRenderer;
import textinput.TextChoice;
import textinput.TextInput;
import tui.Option;

public class CreateOrderOption extends Option {

	OrderController orderController;

	public CreateOrderOption() {
		super("Create Order");
		orderController = new OrderController();
	}

	@Override
	public void start() {
		TextInput textinput = new TextInput();
		orderController.createOrder();
		System.out.println("****** " + getDescription() + "******");
		System.out.println("Scan products now - ");
		scanProducts(textinput);
		if (textinput.promptBoolean("Would you like to change the price of any of the products you scanned?")) {
			editProductPrice(orderController.getCurrentOrder());
		}
		Person currentPerson = null;
		boolean done = false;
		while (!done) {
			currentPerson = selectCostumer(findCustomers());
			if (currentPerson != null) {
				done = true;
			}
		}
		attachCustomer(currentPerson.getPhoneNr());
		finishSale();
		if (textinput.promptBoolean("Print the receipt?")) {
			printReceipt(orderController.getCurrentOrder());
		}
	}

	private void attachCustomer(String phone) {
		orderController.attachCustomer(phone);
	}

	public void printReceipt(Order order) {

		double total = order.getTotal(false);
		double discountedTotal = order.getTotal(true);
		System.out.println("Discounted total: " + discountedTotal + " total: " + total);

		System.out.println("*********************************");
		System.out.println(String.format("* %s\t: %s\t*", "Ordernumber", order.getOrderNumber()));
		System.out.println("*-------------------------------*");
		for (OrderLine line : order.getOrderLineList()) {
			System.out.println(String.format("* %s: %s x %s\t*", formatString(line.getProduct().getName(), 11),
					line.getAmount(), line.getPrice() + " DKK"));
			for(SellableProduct product : line.getProduct().getProducts()) {
				printOrderLine(product, "-");
			}
		}
		System.out.println("*-------------------------------*");
		System.out.println(String.format("* Total price\t: %.2f,-\t*", discountedTotal));
		System.out.println(String.format("* Tax amounts\t: %.2f,-\t*", discountedTotal * 0.20));
		if (discountedTotal != total)
			System.out.println(String.format("* Discount\t: %.2f,-\t*", (total - discountedTotal)));
		System.out.println(String.format("* Cashier\t: %s*", formatString(order.getEmployee().getName(), 11)));
		switch (order.getPayment()) {
		case cash:
			System.out.println("* Paid with cash\t\t*");
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

	private void printOrderLine(SellableProduct product, String indent) {
		System.out.println(String.format("* %s %s: %s x %s\t*", indent, formatString(product.getName(), 9),
				1, product.getPrice() + " DKK"));
		for(SellableProduct innerProduct : product.getProducts()) {
			printOrderLine(innerProduct, indent + "-");
		}
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
			currProduct = orderController.scanProduct(barcode);
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
					new IListRenderer<OrderLine>() {
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
				orderController.editProductPrice(orderLineList.indexOf(currOrderline), newPrice);
			}

		}
	}

	private void finishSale() {
		Order currOrder = orderController.getCurrentOrder();
		TextChoice<PaymentMethod> paymentChooser = new TextChoice<>("*********************************", true,
				new IListRenderer<PaymentMethod>() {

					@Override
					public String display(PaymentMethod option) {
						return option.name();
					}
				});
		paymentChooser.addOption(PaymentMethod.cash);
		paymentChooser.addOption(PaymentMethod.creditcard);
		if (currOrder.getCustomer().getPhoneNr() == "0000") {
			PaymentMethod choice = paymentChooser.promptMenu("Cash or creditcard?");
			if (choice != null) {
				orderController.finishSale(choice);
			}
		} else {
			orderController.finishSale(PaymentMethod.invoice);
		}
	}

	private List<Person> findCustomers() {
		TextInput textInput = new TextInput();
		String costumerPhone = textInput.promptString("Find customer by phone or name [0000 is cash customer]");
		List<Person> customers = orderController.findCustomers(costumerPhone);
		return customers;
	}

	public Person selectCostumer(List<Person> customers) {
		Person currPerson = new TextChoice<Person>("*********************************", true,
				new IListRenderer<Person>() {
					@Override
					public String display(Person option) {
						return String.format("%s - %s - %s", option.getName(), option.getPhoneNr(), option.getEmail());
					}
				}, customers).promptMenu("choose customer ");
		return currPerson;
	}
}
