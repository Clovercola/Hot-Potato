package me.CloverCola.HotPotato;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationDeserializationUtility;

public class StartArena {

	private static HotPotatoMain plugin;

	public StartArena() {

	}

	public static void setPluginInstance(HotPotatoMain instance) {
		plugin = instance;
	}

	public static void checkIfCanStart(String arenaName) {
		int count = StatusCheck.getPlayerCount(arenaName);
		if (count < 2) {
			return;
		}
		countdown(arenaName);
		return;
	}

	public static void countdown(String arenaName) {
		new BukkitRunnable() {
			//Timer is currently lowered from 30 to 5 for faster testing
			int time = 5;
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
		return;
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
			if (time <= 5 && time > 0) {
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
		StatusCheck.setStarted(arenaName, true);
		ArrayList<Player> playerList = StatusCheck.getAllPlayersFromArena(arenaName);
		LocationManager manager = new LocationManager();
		String locString  = (String) manager.getConfig().get("locations.arenas." + arenaName + ".spawn");
		LocationDeserializationUtility util = new LocationDeserializationUtility();
		Location loc = util.convertStringToLocation(locString);
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).teleport(loc);
		}
		return;
	}

}
