package tui.menu;

import tui.Menu;
import tui.option.CreateOrderOption;
import tui.option.NotImplementedOption;
import tui.option.UpdateOrderOption;
import tui.option.ViewOrderOption;

public class CustomerMenu extends Menu {

	public CustomerMenu() {
		super("Customer Menu");
		//TODO implement statistics
		addOption(new NotImplementedOption());
	}

}
