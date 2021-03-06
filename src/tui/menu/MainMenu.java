package tui.menu;

import tui.Menu;
import tui.option.CreateOrderOption;

public class MainMenu extends Menu {

	public MainMenu() {
		super("Main menu");
		
		super.addOption(new CreateOrderOption());
		super.addOption(new OrderMenu());
		super.addOption(new CustomerMenu());
		super.addOption(new EmployeeMenu());		
		super.addOption(new StatisticsMenu());
	}

}
