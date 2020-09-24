package me.CloverCola.HotPotato.TaggedPlayer;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	private static HashMap<String, BossBar> timerList = new HashMap<String, BossBar>();

	public PotatoTimer() {
		// Empty Constructor
	}

	public static void activateTimer(String arenaName) {
		BossBar timerBar = Bukkit.createBossBar("Fuse", BarColor.RED, BarStyle.SOLID);
		connectToBossBar(arenaName, timerBar);
		timerList.putIfAbsent(arenaName, timerBar);
		new BukkitRunnable() {
			//Countdown temporarily lowered for testing
			int countDown = 10;
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
			}
		}.runTaskTimer(HotPotatoMain.getInstance(), 0, 20);
		return;
	}
	
	private static void connectToBossBar(String arenaName, BossBar timerBar) {
		ArrayList<Player> playerList = StatusCheck.getAllPlayersFromArena(arenaName);
		Player player;
		for (int i = 0; i < playerList.size(); i++) {
			player = playerList.get(i);
			timerBar.addPlayer(player);
		}
		return;
	}
	
	public static void shutDownBossBars() {
		for (String arenaName : timerList.keySet()) {
			timerList.get(arenaName).removeAll();
			timerList.remove(arenaName);
		}
		return;
	}
	
	
}
