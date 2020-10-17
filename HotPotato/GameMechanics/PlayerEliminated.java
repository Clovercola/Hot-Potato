package me.CloverCola.HotPotato.GameMechanics;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.CloverCola.HotPotato.HotPotatoMain;
import me.CloverCola.HotPotato.StatusManager;
import me.CloverCola.HotPotato.Commands.LobbyCommand;
import me.CloverCola.HotPotato.GameMechanics.TaggedPlayer.ChooseTaggedPlayer;

public class PlayerEliminated {
	
	public static void eliminate(String arenaName) {
		Player player = StatusManager.getTaggedFromArena(arenaName);
		player.sendMessage(ChatColor.RED + "You were eliminated! Better luck next time!");
		StatusManager.removePlayer(player);
		PotatoTimer.removeFromBossBar(arenaName, player);
		LobbyCommand.teleportToLobby(player);
		chooseNewTagged(arenaName);
		return;
	}
	
	private static void chooseNewTagged(String arenaName) {
		if (WinCondition.check(arenaName) == true) {
			return;
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				ChooseTaggedPlayer.randomTaggedPlayer(arenaName);
			}
		}.runTaskLater(HotPotatoMain.getInstance(), 60);
		return;
	}

}
