package me.CloverCola.HotPotato.GameMechanics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

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
	
	public static TimerData getTimerData(String arenaName) {
		return timerList.get(arenaName);
	}

	public static void setTimerData(String arenaName, TimerData newData) {
		timerList.put(arenaName, newData);
		return;
	}

	public static void activateTimer(String arenaName) {
		BossBar timerBar = Bukkit.createBossBar("Fuse", BarColor.RED, BarStyle.SOLID);
		connectToBossBar(arenaName, timerBar);
		//Countdown will not be hard-coded as soon as config is created.
		int countdown = 60;
		TimerData timer = new TimerData(timerBar, countdown);
		timerList.put(arenaName, timer);
		new BukkitRunnable() {
			ArrayList<Player> playerList = StatusManager.getAllPlayersFromArena(arenaName);
			
			double progress = 1.0;
			double reduction = 1.0 / timer.getTime();

			@Override
			public void run() {
				if (timer.getTime() - 1 <= 0) {
					timerBar.setProgress(0.0);
					timerBar.removeAll();
					setExpLevelTimer(playerList, 0);
					PlayerEliminated.eliminate(arenaName);
					cancel();
				} else {
					timer.setTime(timer.getTime() - 1);
					Bukkit.getLogger().log(Level.INFO, "Time: " + timer.getTime());
					setExpLevelTimer(playerList, timer.getTime());
					progress -= reduction;
					if (progress < 0) {
						timerBar.setProgress(0);
					} else {
						timerBar.setProgress(progress);
					}
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
	
	private static void setExpLevelTimer(ArrayList<Player> playerList, int time) {
		Player player;
		for (int i = 0; i < playerList.size(); i++) {
			player = playerList.get(i);
			player.setLevel(time);
		}
			
	}

	public static void shutDownBossBars() {
		for (String arenaName : timerList.keySet()) {
			timerList.get(arenaName).getBossBar().removeAll();
		}
		return;
	}

}
