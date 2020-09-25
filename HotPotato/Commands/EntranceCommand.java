package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.ArenaExistance;
import me.CloverCola.HotPotato.ConfigUtilities.LocationFileManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationSerializationUtility;

public class EntranceCommand {

	public EntranceCommand() {
		
	}
	
	public static void checkEntranceCommand(Player player, String[] args) {
		//hotpotato entrance <name>
		if (args.length == 2) {
			String name = args[1];
			createEntrance(player, name);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato entrance <name>");
		return;
	}
	
	private static void createEntrance(Player player, String name) {
		LocationFileManager entranceSetter = new LocationFileManager();
		if (ArenaExistance.doesArenaExist(entranceSetter, name) == false) {
			ArenaExistance.warnMissingArena(player, name);
			return;
		}
		LocationSerializationUtility util = new LocationSerializationUtility();
		String loc = util.createLocationFromPlayer(player);
		entranceSetter.getConfig().set("locations.arenas." + name + ".entrance", loc);
		entranceSetter.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Entrance for " + name + " set!");
		return;
	}
	
}
