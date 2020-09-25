package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.ArenaExistance;
import me.CloverCola.HotPotato.ConfigUtilities.LocationFileManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationSerializationUtility;

public class SpawnCommand {

	public SpawnCommand() {
		
	}
	
	public static void checkSpawnCommand(Player player, String[] args) {
		//hotpotato spawn <arena name>
		if (args.length == 2) {
			String name = args[1];
			setSpawn(player, name);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato spawn <arena>");
		return;
	}
	
	private static void setSpawn(Player player, String name) {
		LocationFileManager spawnSetter = new LocationFileManager();
		if (ArenaExistance.doesArenaExist(spawnSetter, name) == false) {
			ArenaExistance.warnMissingArena(player, name);
			return;
		}
		LocationSerializationUtility util = new LocationSerializationUtility();
		String loc = util.createLocationFromPlayer(player);
		spawnSetter.getConfig().set("locations.arenas." + name + ".spawn", loc);
		spawnSetter.saveConfig();
		player.sendMessage(ChatColor.GOLD + "Arena " + name + " spawn successfully set!");
	}
	
}
