package model;

import java.util.HashMap;
import java.util.Map;

public class LocationContainer {
	private static LocationContainer instance;
	private Map<String, Location> locations;

	private LocationContainer() {
		locations = new HashMap<>();
	}

	public static LocationContainer getInstance() {
		if (instance == null) {
			instance = new LocationContainer();
		}
		return instance;
	}

	public Location getLocation(String address) {
		return locations.get(address);
	}

	public void addLocation(Location location) {
		locations.put(location.getAdress(), location);
	}
}
