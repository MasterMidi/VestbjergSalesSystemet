package textinput;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TextInput {
	private Scanner scanner;

	public TextInput() {
		scanner = new Scanner(System.in);
	}

	public String promptString(String question) {
		printQuestion(question);
		String input = scanner.nextLine();
		return input;
	}

	public int promptInt(String question) {
		int number = -1;

		while (number == -1) {
			try {
				number = Integer.parseInt(promptString(question));
			} catch (NumberFormatException e) {
				System.out.println("Input has to be a number, try again");
			}
		}

		return number;
	}

	public boolean promptBoolean(String question) {
		boolean done = false;
		boolean answer = false;

		while (!done) {
			String input = promptString(question + " (Y/n)");

			switch (input) {
			case "Y":
				answer = true;
			case "n":
				done = true;
				break;
			default:
				question = "Please answer";
			}
		}

		return answer;
	}

	public Date promptDate(String question, String format, String deliminator) {
		DateFormat df = new SimpleDateFormat(format);
		Date date = null;

		while (date == null) {
			try {
				String input = promptString("What date? (" + format + ")").replaceAll("[^\\d]+", deliminator);

				date = df.parse(input);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	private static void printQuestion(String question) {
		System.out.println();
		System.out.print(" -> " + question + ": ");
	}

	public static void printMessage(String message) {
		System.out.println();
		System.out.print(" ->> " + message);
	}
}
