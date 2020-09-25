package me.CloverCola.HotPotato.ConfigUtilities;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

//A utility class for checking if the arena exists.
public class ArenaExistance {

	public ArenaExistance() {

	}

	public static boolean doesArenaExist(LocationFileManager manager, String name) {
		if (manager.getConfig().contains("locations.arenas." + name) == false) {
			return false;
		}
		return true;
	}
	
	public static void warnMissingArena(Player player, String name) {
		player.sendMessage(ChatColor.RED + "The arena " + name + " does not exist!");
	}
	
}
