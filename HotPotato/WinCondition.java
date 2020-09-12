package me.CloverCola.HotPotato;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.Commands.LobbyCommand;

public class WinCondition {
	
	public static void check(String arenaName) {
		if (StatusCheck.getPlayerCount(arenaName) == 1) {
			Player player = StatusCheck.getPlayerFromArena(arenaName, 0);
			player.sendMessage(ChatColor.GOLD + "Congratulations! You won!");
			LobbyCommand.teleportToLobby(player);
			StatusCheck.shutdownArena(arenaName);
			return;
		} else if (StatusCheck.getPlayerCount(arenaName) < 1) {
			StatusCheck.shutdownArena(arenaName);
			return;
		}
		return;
	}

}
