package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationDeserializationUtility;

public class LobbyCommand {

	public LobbyCommand() {
		
	}
	
	public void checkLobbyCommand(Player player, String[] args) {
		int size = args.length;
		if (size == 1) {
			teleportToLobby(player);
			return;
		}
		if (args[1].equalsIgnoreCase("set")) {
			SetLobby options = new SetLobby();
			options.setLobbyOptions(player, args);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato lobby set");
	}
	
	public void teleportToLobby(Player player) {
		//TODO check if lobby exists before teleporting player
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
