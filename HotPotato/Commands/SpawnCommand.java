package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.ArenaExistance;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationSerializationUtility;

public class SpawnCommand {

	public SpawnCommand() {
		
	}
	
	public void checkSpawnCommand(Player player, String[] args) {
		//hotpotato spawn <arena name>
		if (args.length == 2) {
			String name = args[1];
			setSpawn(player, name);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato spawn <arena>");
		return;
	}
	
	private void setSpawn(Player player, String name) {
		LocationManager spawnSetter = new LocationManager();
		if (ArenaExistance.doesArenaExist(spawnSetter, player, name) == false) {
			return;
		}
		LocationSerializationUtility util = new LocationSerializationUtility();
		String loc = util.createLocationFromPlayer(player);
		spawnSetter.getConfig().set("locations.arenas." + name + ".spawn", loc);
	}
	
}
