package textinput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextChoice<T> {
	private String description;
	private String cancelText;
	private String numberComplaint;
	private boolean cancellable;
	private ListRenderer<T> renderer;
	private List<T> options;
	private TextInput textinput = new TextInput();

	public TextChoice(String description, boolean cancellable, ListRenderer<T> renderer) {
		this.description = description;
		this.cancellable = cancellable;
		this.renderer = renderer;

		this.cancelText = "cancel";
		this.numberComplaint = "Input has to be a number, try again";

		this.options = new ArrayList<T>();
		this.options.add(null);
	}
	
	public TextChoice(String description, boolean cancellable, ListRenderer<T> renderer, List<T> options) {
		this(description, cancellable, renderer);
		this.options.addAll(options);
	}

	public boolean addOption(T option) {
		boolean success = false;

		if (option != null) {
			success = options.add(option);
		}

		return success;
	}
	
	public void addOptionAll(List<T> options) {
		this.options.addAll(options);
	}
	
	public void setOptions(List<T> options) {
		this.options = new ArrayList<T>();
		this.options.add(null);
		this.options.addAll(options);
	}

	public T promptMenu(String question) {
		System.out.println(description);

		if (cancellable) {
			printOption(0, cancelText);
		}

		Iterator<T> it = options.listIterator(1);
		for(int i = 1; it.hasNext(); i++) {
			printOption(i, this.renderer.display(it.next()));
		}

		int input = inputChoice(question);

		return options.get(input);
	}

	private int inputChoice(String question) {
		int startIndex = cancellable ? 0 : 1;		
		int input = -1;
		do {
			input = textinput.promptInt(String.format("%s (%s - %s)", question, startIndex, options.size() - 1), numberComplaint);
		} while (input < startIndex || input >= options.size());

		return input;
	}

	private void printOption(int index, String option) {
		System.out.println(String.format("(%s) %s", index, option));
	}

	public void setCancelText(String cancelText) {
		this.cancelText = cancelText;
	}

	public void setNumberComplaint(String numberComplaint) {
		this.numberComplaint = numberComplaint;
	}

	public void setCancellable(boolean cancellable) {
		this.cancellable = cancellable;
	}
}
