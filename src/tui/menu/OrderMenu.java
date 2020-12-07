package tui.menu;

import tui.Menu;
import tui.option.CreateOrderOption;
import tui.option.UpdateOrderOption;
import tui.option.ViewOrderOption;

public class OrderMenu extends Menu {

	public OrderMenu() {
		super("Order Menu");
		super.addOption(new CreateOrderOption());
		super.addOption(new ViewOrderOption());
		super.addOption(new UpdateOrderOption());
	}

}
