package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusCheck;

public class LeaveCommand {

	public LeaveCommand() {
		
	}
	
	public static void leaveArena(Player player) {
		if (StatusCheck.isInArena(player) == false) {
			player.sendMessage(ChatColor.RED + "You aren't in an arena!");
			return;
		}
		StatusCheck.leave(player);
		player.sendMessage(ChatColor.GOLD + "You have left the arena!");
	}
	
}
