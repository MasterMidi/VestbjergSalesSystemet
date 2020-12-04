package util;

import java.io.IOException;

public class Console {

	/**
	 * A utility command for clearing the console (Doesn't work for eclipse, because securtity)
	 */
	public static void flush() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
