package me.CloverCola.HotPotato.StorageUtilities;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationSerializationUtility {

	public LocationSerializationUtility() {

	}

	public String createLocationFromPlayer(Player player) {
		Location loc = player.getLocation();
		String locString = convertLocationToString(loc);
		return locString;
	}
	
	public String convertLocationToString(Location loc) {
		if (loc == null) {
			return "";
		}
		String world = loc.getWorld().getName();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		return world + ", " + x + ", " + y + ", " + z;
	}

	public String createLocationFromCordsLite(Player player, List<String> parts) {
		String world = player.getWorld().getName();
		String x = parts.get(0);
		String y = parts.get(1);
		String z = parts.get(2);
		String locString = world + ", " + x + ", " + y + ", " + z;
		return locString;
	}

	public String createLocationFromCordsFull(Player player, List<String> parts) {
		// TODO clean up classes here later
		String world = player.getWorld().getName();
		String x = parts.get(0);
		String y = parts.get(1);
		String z = parts.get(2);
		Double yaw = Double.parseDouble(parts.get(3));
		Double pitch = Double.parseDouble(parts.get(4));
		String locString = world + ", " + x + ", " + y + ", " + z + ", " + yaw + ", " + pitch;
		return locString;
	}

}
