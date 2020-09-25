package me.CloverCola.HotPotato;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationDeserializationUtility;
import me.CloverCola.HotPotato.TaggedPlayer.ChooseTaggedPlayer;
import me.CloverCola.HotPotato.TaggedPlayer.PotatoTimer;

public class StartArena {

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
			// Timer is currently lowered from 30 to 5 for faster testing
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
		}.runTaskTimer(HotPotatoMain.getPlugin(), 20, 20);
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
		Location loc = getSpawnLoc(arenaName);
		Player player;
		for (int i = 0; i < playerList.size(); i++) {
			player = playerList.get(i);
			player.teleport(loc);
			player.sendMessage(ChatColor.GOLD + "The first player will be chosen in 3 seconds!");
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				ChooseTaggedPlayer.randomTaggedPlayer(arenaName);
				PotatoTimer.activateTimer(arenaName);
			}
		}.runTaskLater(HotPotatoMain.getPlugin(), 60);
		return;
	}

	private static Location getSpawnLoc(String arenaName) {
		LocationManager manager = new LocationManager();
		String locString = (String) manager.getConfig().get("locations.arenas." + arenaName + ".spawn");
		LocationDeserializationUtility util = new LocationDeserializationUtility();
		Location loc = util.convertStringToLocation(locString);
		return loc;
	}

}
