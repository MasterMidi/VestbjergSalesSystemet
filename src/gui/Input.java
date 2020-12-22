package gui;

import javax.swing.JOptionPane;

import model.sale.PaymentMethod;

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

	public static PaymentMethod getPaymentInput(String message, String title, PaymentMethod[] listOfEnums) {
		PaymentMethod res = null;
		int index = JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, listOfEnums, null);
		if (index != -1) {
			res = listOfEnums[index];
		}

		return res;

	}

}
