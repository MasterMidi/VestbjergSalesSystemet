package main;

import java.util.Random;

public class FakeData {
	public static String[] names = {
			"Søm",
			"Hammer",
			"Skruetrækker",
			"Svensk nøgle", 
			"Ildtang",
			"Muggert",
			"Dolk", 
			"Skruer",
			"Vinkel sliber",
			"Bormaskine",
			"Støvsuger",
			"Søm pistol", 
			"Lim pistol",
			"Slagbord",
			"Beton mixer",
			"Beton mix pose",
			"Copper", 
			"Jern",
			"Maling",
			"Sav",
			"Nedstryger",
			"Højtryks renser", 
			"Kost" //23
			
	
	};
	
	public static String[] units = {"g","kg","ton"};
	
	public static String[] colors = {"Grøn","Blå","Sort","Hvid","lyse blå","lyse grøn","mørke grøn","mørke blå","rød","lilla","violet","Gul","hudfarvet"}; // 14
	public static String[] sizes = {"lille","stor","meget stor","meget lille","kæmpe"}; //6 
	public static String[] manufactorer = {"Bosch", "nilfisk", "silvan", "ALLUX","Vileda","RoLeX", "Gucci","versace", "Adidas","kurnigsægt"}; //10
	public static String[] addictives = {"smukke","fandens","grimme","mindre pæne","flotte","erotisk buttet", "aggresiv","små glad ;) ","sarkastisk"}; //9
	
	public static String getName()
	{
		Random r = new Random();
		int nameIndex = r.nextInt();
		int unitIndex = r.nextInt();
		int sizeIndex = r.nextInt();
		int colorIndex = r.nextInt();
		int manufactorIndex = r.nextInt();
		int addIndex = r.nextInt();
		
		
		return "Magnus lugter";
	}
}
