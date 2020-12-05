package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import textinput.TextInput;

public class Main {
	public static void main(String[] args) {
//		new Menu("Main menu").start();

		Date date = new TextInput().promptDate("Whats the date?", "dd/MM/yyyy");
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(df.format(date));
	}
}
