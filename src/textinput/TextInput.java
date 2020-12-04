package textinput;

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

	public int promptInt(String question, String complaint) {
		int number = 0;
		boolean done = false;

		while (!done) {
			try {
				number = Integer.parseInt(promptString(question));
				done = true;
			} catch (NumberFormatException e) {
				System.out.println(complaint);
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

	private static void printQuestion(String question) {
		System.out.println();
		System.out.print(" -> " + question + ": ");
	}

	public static void printMessage(String message) {
		System.out.println();
		System.out.print(" ->> " + message);
	}
}
