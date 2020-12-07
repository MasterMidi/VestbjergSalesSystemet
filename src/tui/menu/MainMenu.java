package tui.menu;

import tui.Menu;

public class MainMenu extends Menu {

	public MainMenu() {
		super("Main menu");

		super.addOption(new OrderMenu());
	}

}
