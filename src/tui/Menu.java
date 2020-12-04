package tui;

import java.util.ArrayList;
import java.util.List;

import textinput.TextChoice;
import textinput.renderers.SellectableRenderer;
import util.Console;

public class Menu implements Selectable {
	private String description;
	private String cancelText;
	private List<Selectable> options;
	private TextChoice<Selectable> textchoice;

	public Menu(String description) {
		this.description = description;
		this.cancelText = "Exit";

		options = new ArrayList<>();		
		textchoice = new TextChoice<>("****** " + description + " ******", true, new SellectableRenderer());
	}

	public void start() {
		textchoice.setOptions(options);
		Console.flush();
		boolean done = false;
		while (!done) {
			done = choose();
		}
		Console.flush();
	}

	public boolean choose() {
		boolean exit = false;

		textchoice.setCancelText(cancelText);
		Selectable choice = textchoice.promptMenu("Pick an option");

		if (choice != null) {
			choice.start();
		} else {
			exit = true;
			System.out.println("Bye!");
		}

		return exit;
	}

	/**
	 * Method to add new options to the menu.
	 * 
	 * @param option the option to add to the list of options
	 */
	public void addOption(Selectable option) {
		options.add(option);
	}

	/**
	 * A method to retrieve the list of options in this menu.
	 * 
	 * @return The list of available options in this menu
	 */
	public List<Selectable> getOptions() {
		return new ArrayList<>(options);
	}

	public String getDescription() {
		return description;
	}

	public void setCancelText(String cancelText) {
		this.cancelText = cancelText;
	}

	public void setTextchoice(TextChoice<Selectable> textchoice) {
		this.textchoice = textchoice;
	}
}
