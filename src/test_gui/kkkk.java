package test_gui;

import javax.swing.JPanel;
import javax.swing.JSlider;
import gui.JHintTextField;

public class kkkk extends JPanel {

	/**
	 * Create the panel.
	 */
	public kkkk() {
		
		JHintTextField hintTextField = new JHintTextField();
		add(hintTextField);
		hintTextField.setText("hey there");
		
		JSlider slider = new JSlider();
		add(slider);

	}

}
