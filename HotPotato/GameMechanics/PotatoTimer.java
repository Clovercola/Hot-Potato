package me.CloverCola.HotPotato.GameMechanics;

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
import me.CloverCola.HotPotato.StatusManager;
import me.CloverCola.HotPotato.DataClasses.TimerData;

public class PotatoTimer implements Listener {

	private static HashMap<String, TimerData> timerList = new HashMap<String, TimerData>();

	public PotatoTimer() {
		// Empty Constructor
	}

	public static void activateTimer(String arenaName) {
		BossBar timerBar = Bukkit.createBossBar("Fuse", BarColor.RED, BarStyle.SOLID);
		connectToBossBar(arenaName, timerBar);
		// Countdown temporarily lowered for testing
		int countdown = 5;
		TimerData timer = new TimerData(timerBar, countdown);
		timerList.put(arenaName, timer);
		new BukkitRunnable() {
			double progress = 1.0;
			double reduction = 1.0 / timer.getTime();

			@Override
			public void run() {
				if (timer.getTime() == 0) {
					timerBar.setProgress(0.0);
					timerBar.removeAll();
					PlayerEliminated.eliminate(arenaName);
					cancel();
				} else {
					timer.setTime(timer.getTime() - 1);
					progress -= reduction;
					timerBar.setProgress(progress);
				}
			}
		}.runTaskTimer(HotPotatoMain.getInstance(), 0, 20);
		return;
	}

	private static void connectToBossBar(String arenaName, BossBar timerBar) {
		ArrayList<Player> playerList = StatusManager.getAllPlayersFromArena(arenaName);
		Player player;
		for (int i = 0; i < playerList.size(); i++) {
			player = playerList.get(i);
			timerBar.addPlayer(player);
		}
		return;
	}

	public static void shutDownBossBars() {
		for (String arenaName : timerList.keySet()) {
			timerList.get(arenaName).getBossBar().removeAll();
		}

		return;
	}

}
