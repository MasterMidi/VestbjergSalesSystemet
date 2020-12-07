package tui.menu;

import tui.Menu;
import tui.option.CreateOrderOption;
import tui.option.NotImplementedOption;
import tui.option.UpdateOrderOption;
import tui.option.ViewOrderOption;

public class EmployeeMenu extends Menu {

	public EmployeeMenu() {
		super("Employee Menu");
		//TODO implement statistics
		addOption(new NotImplementedOption());
	}

}
