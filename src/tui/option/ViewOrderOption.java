package tui.option;

import controller.OrderController;
import textinput.TextInput;
import tui.Option;

public class ViewOrderOption extends Option {

	public ViewOrderOption() {
		super("View Order");
	}
	
	@Override
	//Mock data for create order.
	public void start() {
		OrderController orderCon = new OrderController();
		System.out.println("****** " + getDescription() + "******");
		//TODO Implement function.
		System.out.println("Method not implemented yet..");
	}


}
