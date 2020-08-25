package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.ArenaExistance;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;

public class DisableCommand {
	
	public DisableCommand() {
		
	}
	
	public void disableCommandChecker(Player player, String[] args) {
		//hotpotato disable <arena>
		if (args.length == 2) {
			disableArena(player, args);
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato disable <arena>");
		return;
	}
	
	public void disableArena(Player player, String[] args) {
		LocationManager enable = new LocationManager();
		String name = args[1];
		if (ArenaExistance.doesArenaExist(enable, player, name) == false) {
			return;
		}
		if (enable.getConfig().getBoolean("locations.arenas." + name + ".enabled") == true) {
			player.sendMessage(ChatColor.RED + "That arena is already enabled!");
			return;
		}
		enable.getConfig().set("locations.arenas." + name + ".enabled", true);
		enable.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been enabled!");
		return;
	}
	
}
