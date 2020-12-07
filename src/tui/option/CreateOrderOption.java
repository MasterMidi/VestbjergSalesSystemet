package tui.option;

import controller.OrderController;
import textinput.TextInput;
import tui.Option;

public class CreateOrderOption extends Option {

	public CreateOrderOption() {
		super("Create Order");
	}
	
	@Override
	//Mock data for create order.
	public void start() {
		OrderController orderCon = new OrderController();
		System.out.println("****** " + getDescription() + "******");
		//TODO Implement function.
		System.out.println("Scan products now - ");
		
		TextInput textinput = new TextInput();
		String barcode = textinput.promptString("Enter Barcode: ");
	}


}
