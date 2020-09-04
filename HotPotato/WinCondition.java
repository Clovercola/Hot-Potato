package me.CloverCola.HotPotato;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WinCondition {

	// Very placeholder win check, I'm aware. I need sleep but I wanted to get
	// something that somewhat resembles the actual
	// format for the finished product done.
	public static void check(String arenaName) {
		if (StatusCheck.getPlayerCount(arenaName) == 1) {
			Player player = StatusCheck.getPlayerFromArena(arenaName, 0);
			player.sendMessage(ChatColor.GOLD + "You won!");
		} else if (StatusCheck.getPlayerCount(arenaName) < 1) {
			// Shut down arena
		}
	}

}
