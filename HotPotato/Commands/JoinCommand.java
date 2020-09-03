package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusCheck;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationDeserializationUtility;

public class JoinCommand {

	public JoinCommand() {

	}

	public static void joinArena(Player player, String[] args) {
		if (args.length != 2) {
			joinUsage(player);
			return;
		}
		String arena = args[1];
		if (StatusCheck.isInArena(player) == true) {
			player.sendMessage(ChatColor.RED + "You're already in a game!");
			return;
		}
		LocationManager check = new LocationManager();
		if (check.getConfig().contains("locations.arenas." + arena) == false) {
			player.sendMessage(ChatColor.RED + "That arena does not exist!");
			return;
		}
		if (check.getConfig().getBoolean("locations.arenas." + arena + ".enabled") == false) {
			player.sendMessage(ChatColor.RED + "That arena is currently disabled and cannot be joined right now!");
			return;
		}
		if (StatusCheck.hasStarted(arena) == true) {
			player.sendMessage(ChatColor.RED + "That arena has already started!");
			return;
		}
		StatusCheck.join(player, arena);
		teleportToEntrance(arena, player);
		player.sendMessage(ChatColor.GOLD + "You have joined arena " + arena + "!");
	}

	private static void joinUsage(Player player) {
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato join <arena>");
	}
	
	private static void teleportToEntrance(String arenaName, Player player) {
		LocationManager manager = new LocationManager();
		LocationDeserializationUtility util = new LocationDeserializationUtility();
		String locString = (String) manager.getConfig().get("locations.arenas." + arenaName + ".entrance");
		Location loc = util.convertStringToLocation(locString);
		player.teleport(loc);
	}

}
