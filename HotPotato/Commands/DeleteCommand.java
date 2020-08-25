package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;

public class DeleteCommand {

	public DeleteCommand() {
		
	}
	
	public void checkDeleteCommand(Player player, String[] args) {
		if (args.length == 2) {
			deleteArena(player, args);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato delete <arena>");
		return;
	}
	
	private void deleteArena(Player player, String[] args) {
		String name = args[1];
		LocationManager delete = new LocationManager();
		delete.getConfig().set("locations.arenas." + name, null);
		delete.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been deleted!");
		return;
	}
	
}
