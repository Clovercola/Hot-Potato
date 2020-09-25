package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.ArenaExistance;
import me.CloverCola.HotPotato.ConfigUtilities.LocationFileManager;

public class DisableCommand {
	
	public DisableCommand() {
		
	}
	
	public static void disableCommandChecker(Player player, String[] args) {
		if (args.length == 2) {
			disableArena(player, args);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato disable <arena>");
		return;
	}
	
	public static void disableArena(Player player, String[] args) {
		LocationFileManager disable = new LocationFileManager();
		String name = args[1];
		if (ArenaExistance.doesArenaExist(disable, name) == false) {
			ArenaExistance.warnMissingArena(player, name);
			return;
		}
		if (disable.getConfig().getBoolean("locations.arenas." + name + ".enabled") == false) {
			player.sendMessage(ChatColor.RED + "That arena is already disabled!");
			return;
		}
		disable.getConfig().set("locations.arenas." + name + ".enabled", false);
		disable.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been disabled!");
		return;
	}
	
}
