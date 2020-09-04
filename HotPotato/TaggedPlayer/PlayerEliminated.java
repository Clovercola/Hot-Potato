package me.CloverCola.HotPotato.TaggedPlayer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusCheck;

public class PlayerEliminated {

	// This class will change a LOT.
	//It's pretty barebones right now because I'm cleaning up my code next.
	//So this will get a method or two in order to get the tagged player directly.
	public static void eliminate(String arenaName) {
		Player player;
		for (int i = 0; i < StatusCheck.getPlayerCount(arenaName); i++) {
			player = StatusCheck.getPlayerFromArena(arenaName, i);
			if (StatusCheck.isTagged(player) == true) {
				player.sendMessage(ChatColor.RED + "You were eliminated!");
				StatusCheck.leave(player);
				break;
			}
		}
	}

}
