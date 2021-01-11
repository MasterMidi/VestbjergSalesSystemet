package main;

import java.text.DecimalFormat;
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
	
	public static String[] colors = {"grønne","blå","sorte","hvide","lyse blå","lyse grønne","mørke grønne","mørke blå","røde","lilla","violette","gule","hudfarvet"}; // 14
	public static String[] sizes = {"lille","store","meget store","meget lille","kæmpe"}; //6 
	public static String[] manufactorer = {"Bosch", "nilfisk", "silvan", "ALLUX","Vileda","RoLeX", "Gucci","versace", "Adidas","kurnigsægt"}; //10
	public static String[] addictives = {"smukke","fandens","grimme","mindre pæne","flotte","erotisk buttet", "aggresiv","små glad","sarkastisk"}; //9
	//advektiv, size, color, navn fra manufaktorer
	private static DecimalFormat df2 = new DecimalFormat("####,##");
	private static Random r = new Random();
	
	
	public static String getName()
	{
		int nameIndex = r.nextInt(names.length);
		int unitIndex = r.nextInt(units.length);
		int sizeIndex = r.nextInt(sizes.length);
		int colorIndex = r.nextInt(colors.length);
		int manufactorIndex = r.nextInt(manufactorer.length);
		int addIndex = r.nextInt(addictives.length);
		String fakeName = String.format("%s %s %s %s fra %s",addictives[addIndex], sizes[sizeIndex],colors[colorIndex],names[nameIndex],manufactorer[manufactorIndex]  );
		
		
		return fakeName;
	}
	public static int getStock(int i) {
		return r.nextInt(i);
		
	}
	
	public static Double getPrice(int i) {
		double res = r.nextInt(i) + 0.95d;
		return res;
		
	}
}
