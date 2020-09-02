package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationDeserializationUtility;

public class LobbyCommand {

	public LobbyCommand() {
		
	}
	
	public static void checkLobbyCommand(Player player, String[] args) {
		int size = args.length;
		if (size == 1) {
			teleportToLobby(player);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato lobby set");
	}
	
	public static void teleportToLobby(Player player) {
		LocationManager lobby = new LocationManager();
		String locString = (String) lobby.getConfig().get("locations.lobby");
		if (locString.isEmpty() == true) {
			player.sendMessage(ChatColor.RED + "There isn't currently a Hot Potato lobby!");
			return;
		}
		LocationDeserializationUtility util = new LocationDeserializationUtility();
		Location loc = util.convertStringToLocation(locString, player);
		player.teleport(loc);
	}
	
}
