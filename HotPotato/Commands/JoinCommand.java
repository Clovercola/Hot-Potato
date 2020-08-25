package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusCheck;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;
import me.CloverCola.HotPotato.DataClasses.PlayerArenaStatus;

public class JoinCommand {

	public JoinCommand() {

	}

	public void joinArena(Player player, String[] args) {
		if (args.length != 2) {
			joinUsage(player);
			return;
		}
		String arena = args[1];
		StatusCheck manage = new StatusCheck();
		if (manage.isInArena(player) == true) {
			player.sendMessage(ChatColor.RED + "You're already in an arena!");
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
		PlayerArenaStatus join = new PlayerArenaStatus(player, arena);
		manage.joinArenaStatus(player, join);
		player.sendMessage(ChatColor.GOLD + "You have joined arena " + arena + "!");
	}

	private void joinUsage(Player player) {
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato join <arena>");
	}

}
