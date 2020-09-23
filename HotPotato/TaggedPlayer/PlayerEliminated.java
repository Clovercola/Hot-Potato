package me.CloverCola.HotPotato.TaggedPlayer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.CloverCola.HotPotato.HotPotatoMain;
import me.CloverCola.HotPotato.StatusCheck;
import me.CloverCola.HotPotato.WinCondition;

public class PlayerEliminated {
	
	public static void eliminate(String arenaName) {
		Player player = StatusCheck.getTaggedFromArena(arenaName);
		player.sendMessage(ChatColor.RED + "You were eliminated! Better luck next time!");
		StatusCheck.removePlayer(player);
		StatusCheck.setTaggedInArena(null);
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
		}.runTaskLater(HotPotatoMain.getPlugin(), 60);
		return;
	}

}
