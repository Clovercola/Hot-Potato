package me.CloverCola.HotPotato.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.LocationFileManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationSerializationUtility;

public class SetLobby {

	public SetLobby() {

	}
	
	public static void checkSetCommand(Player player, String[] args) {
		int size = args.length;
		if (size == 1 || size == 4 || size == 6) {
			setLobbyOptions(player, args);
			return;
		}
		player.sendMessage(ChatColor.RED + "Usage: /hotpotato set [optional coordinates]");
		return;
	}

	private static void setLobbyOptions(Player player, String[] args) {
		int setChoice = args.length;
		switch (setChoice) {
		case 1:
			// /HotPotato lobby set
			// Since it's only using "set", set lobby with player location.
			LocationSerializationUtility utilSimple = new LocationSerializationUtility();
			Location loc = player.getLocation();
			String write2 = utilSimple.convertLocationToString(loc);
			writeLobby(write2, player);
			return;
		case 4:
			// /HotPotato lobby set x y z
			LocationSerializationUtility locConvLite = new LocationSerializationUtility();
			List<String> partsLite = new ArrayList<String>();
			partsLite.add(args[2]);
			partsLite.add(args[3]);
			partsLite.add(args[4]);
			String write5 = locConvLite.createLocationFromCordsLite(player, partsLite);
			writeLobby(write5, player);
			return;
		case 6:
			// /HotPotato lobby set x y z yaw pitch
			LocationSerializationUtility locConvFull = new LocationSerializationUtility();
			List<String> partsFull = new ArrayList<String>();
			partsFull.add(args[2]);
			partsFull.add(args[3]);
			partsFull.add(args[4]);
			partsFull.add(args[5]);
			partsFull.add(args[6]);
			String write7 = locConvFull.createLocationFromCordsFull(player, partsFull);
			writeLobby(write7, player);
			return;
		default:
			// TODO
		}
	}
	
	private static void writeLobby(String locString, Player player) {
		LocationFileManager lobby = new LocationFileManager();
		try {
			lobby.getConfig().set("locations.lobby", locString);
			lobby.saveConfig();
		}catch (Exception e){
			Bukkit.getLogger().log(Level.SEVERE, "Failed to save lobby to the file!");
		}
		player.sendMessage(ChatColor.GOLD + "Lobby set successfully!");
	}

}
