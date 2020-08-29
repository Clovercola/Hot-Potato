package me.CloverCola.HotPotato;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartArena {

	private static HotPotatoMain plugin;

	public StartArena() {

	}

	public static void setPluginInstance(HotPotatoMain instance) {
		plugin = instance;
	}

	public static boolean checkIfCanStart(String arenaName) {
		int count = StatusCheck.getPlayerCount(arenaName);
		if (count < 2) {
			return false;
		}
		return true;
	}

	public static void countdown(String arenaName) {
		new BukkitRunnable() {
			int time = 30;

			@Override
			public void run() {
				if (time <= 0) {
					start(arenaName);
					cancel();
				}
				if (checkIfShouldNotify(time) == true) {
					notification(arenaName, time);
				}
				time--;
			}
		}.runTaskTimer(plugin, 20, 20);
	}

	private static boolean checkIfShouldNotify(int time) {
		switch (time) {
		case 30:
			return true;
		case 20:
			return true;
		case 10:
			return true;
		default:
			if (time <= 5) {
				return true;
			} else {
				return false;
			}
		}
	}

	private static void notification(String arenaName, int time) {
		ArrayList<Player> playerList = StatusCheck.getAllPlayersFromArena(arenaName);
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).sendMessage(ChatColor.GREEN + "The game will start in " + time + " seconds!");
		}
		return;
	}

	public static void start(String arenaName) {
		ArrayList<Player> playerList = StatusCheck.getAllPlayersFromArena(arenaName);
		for (int i = 0; i < playerList.size(); i++) {
			//TODO
			playerList.get(i).sendMessage(
					ChatColor.GREEN + "This is where you WOULD be teleported to the game, if it was implemented yet!");
		}
		return;
	}

}
