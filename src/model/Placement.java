package model;

public class Placement {
	private int row;
	private int shelf;
	private int section;
	private Location location;

	public Placement(int row, int shelf, int section, String location) {
		super();
		this.row = row;
		this.shelf = shelf;
		this.section = section;
		this.location = LocationContainer.getInstance().getLocation(location);
	}

	public int getRow() {
		return row;
	}

	public int getShelf() {
		return shelf;
	}

	public int getSection() {
		return section;
	}

	public Location getLocation() {
		return location;
	}

}
