package model.sale;

public class Location {
	private String adress;
	private String phoneNr;
	private String openingHours;

	public Location(String adress, String phoneNr, String openingHours) {
		super();
		this.adress = adress;
		this.phoneNr = phoneNr;
		this.openingHours = openingHours;
	}

	public String getAdress() {
		return adress;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public String getOpeningHours() {
		return openingHours;
	}
}
