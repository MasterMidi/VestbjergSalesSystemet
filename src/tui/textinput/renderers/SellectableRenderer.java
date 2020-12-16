package tui.textinput.renderers;

import tui.Selectable;
import tui.textinput.IListRenderer;

public class SellectableRenderer implements IListRenderer<Selectable> {

	@Override
	public String display(Selectable option) {
		return option.getDescription();
	}

}
