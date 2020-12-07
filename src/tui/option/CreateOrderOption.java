package tui.option;

import controller.OrderController;
import model.product.SellableProduct;
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
		orderCon.createOrder();
		System.out.println("****** " + getDescription() + "******");
		//TODO Implement function.
		System.out.println("Scan products now - ");
		
		boolean done = false;
		SellableProduct currProduct = null;
		while(!done) {
			TextInput textinput = new TextInput();
			String barcode = textinput.promptString("Enter Barcode [press 0 to stop]: ");
			
			currProduct = orderCon.getProduct(barcode);
			orderCon.getCurrentOrder().addOrderLine(currProduct);
			SellableProduct prod = orderCon.getCurrentOrder().getOrderLineList().get(0).getProduct();
			System.out.println(prod.getName());
			
			if(barcode.equals("0")) {
				done = true;
			}
		}
	}


}
