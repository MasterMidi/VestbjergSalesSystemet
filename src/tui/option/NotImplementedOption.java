package tui.option;

import tui.Option;

public class NotImplementedOption extends Option {
	
	public NotImplementedOption() {
		super("Not implemented..");
	}
	
	@Override
	//Mock data for create order.
	public void start() {
		System.out.println("****** " + getDescription() + "******");
		//TODO Implement function.
		System.out.println("Method not implemented yet..");
	}
}
