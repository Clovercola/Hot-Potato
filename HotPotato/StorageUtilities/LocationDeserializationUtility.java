package me.CloverCola.HotPotato.StorageUtilities;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class LocationDeserializationUtility {
	
	public LocationDeserializationUtility() {
		
	}

	public Location convertStringToLocation(String locString, Player player) {
		if (locString.isEmpty() == true) {
			return player.getLocation();
		}
		List<String> locParts = Arrays.asList(locString.split(","));
		int size = locParts.size();
		switch (size) {
		case 4:
			return locationCreatorLite(locParts);
		case 6:
			return locationCreatorFull(locParts);
		default:
			return null;
		}
	}
	
	private Location locationCreatorLite(List<String> locParts) {
		try {
			World world = Bukkit.getWorld(locParts.get(0));
			double x = Double.parseDouble(locParts.get(1));
			double y = Double.parseDouble(locParts.get(2));
			double z = Double.parseDouble(locParts.get(3));
			Location loc = new Location(world, x, y, z);
			return loc;
		}catch(Exception e) {
			return null;
		}
	}
	
	private Location locationCreatorFull(List<String> locParts) {
		try {
			World world = Bukkit.getWorld(locParts.get(0));
			double x = Double.parseDouble(locParts.get(1));
			double y = Double.parseDouble(locParts.get(2));
			double z = Double.parseDouble(locParts.get(3));
			float yaw = Float.parseFloat(locParts.get(4));
			float pitch = Float.parseFloat(locParts.get(5));
			Location loc = new Location(world, x, y, z, yaw, pitch);
			return loc;
		}catch(Exception e) {
			return null;
		}
	}
	
	public boolean checkIfNotNull(Location loc) {
		if (loc == null) {
			return false;
		}
		if (loc.getWorld() == null) {
			return false;
		}
		return true;
	}
	
}
