package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.ArenaExistance;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;

public class EnableCommand {

	public EnableCommand() {

	}

	public void enableCommandChecker(Player player, String[] args) {
		//hotpotato enable <Arena>
		if (args.length == 2) {
			enableArena(player, args);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato enable <arena>");
		return;
	}
	
	public void enableArena(Player player, String[] args) {
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
