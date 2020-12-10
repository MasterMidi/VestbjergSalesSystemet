package textinput.renderers;

import textinput.IListRenderer;
import tui.Selectable;

public class SellectableRenderer implements IListRenderer<Selectable> {

	@Override
	public String display(Selectable option) {
		return option.getDescription();
	}

}
