package me.CloverCola.HotPotato;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.Commands.LobbyCommand;

public class WinCondition {
	
	public static boolean check(String arenaName) {
		if (StatusCheck.getPlayerCount(arenaName) == 1) {
			endGame(arenaName);
			return true;
		} else if (StatusCheck.getPlayerCount(arenaName) < 1) {
			StatusCheck.shutdownArena(arenaName);
			return true;
		}
		return false;
	}
	
	private static void endGame(String arenaName) {
		Player player = StatusCheck.getPlayerFromArena(arenaName, 0);
		player.sendMessage(ChatColor.GOLD + "Congratulations! You won!");
		StatusCheck.removePlayer(player);
		LobbyCommand.teleportToLobby(player);
		StatusCheck.shutdownArena(arenaName);
		return;
	}

}
