package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.MetaHandler;
import me.CloverCola.HotPotato.StatusManager;

public class LeaveCommand {
	
	public static void leaveArena(Player player) {
		if (MetaHandler.inArena(player) == false) {
			player.sendMessage(ChatColor.RED + "You aren't in an arena!");
			return;
		}
		StatusManager.leave(player);
		LobbyCommand.teleportToLobby(player);
	}
	
}
