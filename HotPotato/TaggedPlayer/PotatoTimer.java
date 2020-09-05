package me.CloverCola.HotPotato.TaggedPlayer;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.CloverCola.HotPotato.HotPotatoMain;
import me.CloverCola.HotPotato.StatusCheck;

public class PotatoTimer implements Listener {

	public PotatoTimer() {
		// Empty Constructor
	}

	public static void activateTimer(String arenaName) {
		BossBar timerBar = Bukkit.createBossBar("Test Bar", BarColor.RED, BarStyle.SOLID);
		ArrayList<Player> playerList = StatusCheck.getAllPlayersFromArena(arenaName);
		Player player;
		for (int i = 0; i < playerList.size(); i++) {
			player = playerList.get(i);
			timerBar.addPlayer(player);
		}
		new BukkitRunnable() {
			int countDown = 30;
			double progress = 1.0;
			double lower = 1.0 / countDown;
			@Override
			public void run() {
				if (countDown == 0) {
//					player.sendMessage("The potato has exploded!");
					timerBar.setProgress(0.0);
					timerBar.removeAll();
					PlayerEliminated.eliminate(arenaName);
					cancel();
				}
				else {
//					player.sendMessage("Time left: " + countDown);
					countDown -= 1;
					progress -= lower;
					timerBar.setProgress(progress);
				}
			}//End of run
		}.runTaskTimer(HotPotatoMain.getPlugin(), 0, 20);
	}//End of method
}
