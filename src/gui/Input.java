package gui;

import javax.swing.JOptionPane;

public class Input {

	public static Double getInputDouble(String message) {
		Double res = null;
		String input = JOptionPane.showInputDialog(message);
		try {
			res = Double.parseDouble(input);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Input skal være et tal");

		} catch (Exception e) {

		}
		return res;
	}

	public static Integer getInputInteger(String message) {
		Integer res = null;
		String input = JOptionPane.showInputDialog(message);
		try {
			res = Integer.parseInt(input);
		} catch (Exception e) {
		}
		return res;
	}

	public static String getInputString(String message) {
		String res = JOptionPane.showInputDialog(message);
		return res;
	}

}
