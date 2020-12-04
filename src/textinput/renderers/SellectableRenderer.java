package textinput.renderers;

import textinput.ListRenderer;
import tui.Selectable;

public class SellectableRenderer implements ListRenderer<Selectable> {

	@Override
	public String display(Selectable option) {
		return option.getDescription();
	}

}
