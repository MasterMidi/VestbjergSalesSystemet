package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import textinput.TextInput;
import tui.menu.MainMenu;

public class Main {
	public static void main(String[] args) {
		new MainMenu().start();
		
		Calendar c = Calendar.getInstance();
		Date dat = c.getTime();
		System.out.println(dat);

		Date date = new TextInput().promptDate("Whats the date?", "dd-MM-yyyy", "-");
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(df.format(date));
		System.out.println(date.before(dat));
	}
}
