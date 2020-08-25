package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.ArenaExistance;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;

public class CreateCommand {

	public CreateCommand() {

	}

	public void checkCreateCommand(Player player, String[] args) {
		if (args.length == 2) {
			createArena(player, args[1]);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato create <Name>");
	}

	public void createArena(Player player, String name) {
		LocationManager arenaCreator = new LocationManager();
		if (ArenaExistance.doesArenaExist(arenaCreator, player, name) == true) {
			player.sendMessage(ChatColor.RED + "The arena " + name + " already exists!");
			return;
		}
		arenaCreator.getConfig().set("locations.arenas." + name + ".enabled", false);
		arenaCreator.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been created, and is currently disabled.");
		player.sendMessage(ChatColor.GREEN + "Set up the arena before you enable it!");
	}

}