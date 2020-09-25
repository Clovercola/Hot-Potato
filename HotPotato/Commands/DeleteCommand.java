package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.LocationFileManager;

public class DeleteCommand {

	public DeleteCommand() {
		
	}
	
	public static void checkDeleteCommand(Player player, String[] args) {
		if (args.length == 2) {
			deleteArena(player, args);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato delete <arena>");
		return;
	}
	
	private static void deleteArena(Player player, String[] args) {
		String name = args[1];
		LocationFileManager delete = new LocationFileManager();
		if (delete.getConfig().contains("locations.arenas." + name) == false) {
			player.sendMessage(ChatColor.RED + "Arena " + name + " does not exist!");
			return;
		}
		delete.getConfig().set("locations.arenas." + name, null);
		delete.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been deleted!");
		return;
	}
	
}
