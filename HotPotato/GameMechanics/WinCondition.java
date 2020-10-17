package me.CloverCola.HotPotato.GameMechanics;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusManager;
import me.CloverCola.HotPotato.Commands.LobbyCommand;

public class WinCondition {
	
	public static boolean check(String arenaName) {
		if (StatusManager.getPlayerCount(arenaName) == 1) {
			endGame(arenaName);
			return true;
		} else if (StatusManager.getPlayerCount(arenaName) < 1) {
			StatusManager.shutdownArena(arenaName);
			return true;
		}
		return false;
	}
	
	private static void endGame(String arenaName) {
		Player player = StatusManager.getPlayerFromArena(arenaName, 0);
		player.sendMessage(ChatColor.GOLD + "Congratulations! You won!");
		StatusManager.removePlayer(player);
		LobbyCommand.teleportToLobby(player);
		PotatoTimer.emptyBossBar(arenaName);
		StatusManager.shutdownArena(arenaName);
		return;
	}

}
