package tui.menu;

import tui.Menu;
import tui.option.CreateOrderOption;

public class OrderMenu extends Menu {

	public OrderMenu() {
		super("Order Menu");
		super.addOption(new CreateOrderOption());
	}

}
