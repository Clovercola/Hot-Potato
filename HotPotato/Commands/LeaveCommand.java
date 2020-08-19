package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusCheck;

public class LeaveCommand {

	public LeaveCommand() {
		
	}
	
	public void leaveArena(Player player) {
		StatusCheck check = new StatusCheck(); 
		if (check.isInArena(player) == false) {
			player.sendMessage(ChatColor.RED + "You aren't in an arena!");
			return;
		}
		check.leaveArenaStatus(player);
		player.sendMessage(ChatColor.GOLD + "You have left the arena!");
	}
	
}
