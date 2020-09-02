package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusCheck;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;

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
		player.sendMessage(ChatColor.GOLD + "You have joined arena " + arena + "!");
	}

	private static void joinUsage(Player player) {
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato join <arena>");
	}

}
