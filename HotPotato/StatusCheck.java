package me.CloverCola.HotPotato;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import me.CloverCola.HotPotato.DataClasses.ArenaStatus;
import me.CloverCola.HotPotato.DataClasses.InventoryStatusObject;
import me.CloverCola.HotPotato.DataClasses.PlayerArenaStatus;

public class StatusCheck {

	private static HotPotatoMain plugin;
	private static HashMap<String, ArenaStatus> arenaList = new HashMap<String, ArenaStatus>();

	public StatusCheck() {

	}

	public static void getPluginInstance(HotPotatoMain instance) {
		plugin = instance;
	}

	public static int getPlayerCount(String arenaName) {
		return arenaList.get(arenaName).getWaitingPlayers().size();
	}

	public static Player getPlayerFromArena(String arenaName, int slot) {
		return arenaList.get(arenaName).getWaitingPlayers().get(slot);
	}

	public static void join(Player player, String arenaName) {
		if (arenaList.containsKey(arenaName) == false) {
			ArenaStatus status = new ArenaStatus(player);
			arenaList.put(arenaName, status);
		} else {
			arenaList.get(arenaName).getWaitingPlayers().add(player);
		}
		storeInventory(player);
		setJoinedMeta(player, arenaName);
		int count = getPlayerCount(arenaName);
		if (count == 1) {
			player.sendMessage(ChatColor.GREEN + "There is now 1 player waiting!");
		} else {
			player.sendMessage(ChatColor.GREEN + "There is now " + count + " players waiting!");
		}
		return;
	}

	private static void storeInventory(Player player) {
		InventoryStatusObject inv = new InventoryStatusObject(player);
		PlayerInventoryStorage.storeInventory(player, inv);
		return;
	}

	private static void setJoinedMeta(Player player, String arenaName) {
		PlayerArenaStatus status = new PlayerArenaStatus(player, arenaName);
		FixedMetadataValue data = new FixedMetadataValue(plugin, status);
		player.setMetadata("HotPotatoStatus", data);
		return;
	}

	public static void leave(Player player) {
		if (isInArena(player) == false) {
			player.sendMessage(ChatColor.GREEN + "You're not in a game!");
			return;
		}
		retrieveInventory(player);
		player.removeMetadata("HotPotatoStatus", plugin);
		player.sendMessage(ChatColor.GREEN + "You have left the game!");
	}

	// This method exists as a compliment to the earlier "storeInventory" method.
	private static void retrieveInventory(Player player) {
		PlayerInventoryStorage.retrieveInventory(player);
	}

	public static boolean isInArena(Player player) {
		if (player.hasMetadata("HotPotatoStatus") == false) {
			return false;
		}
		return true;
	}

	public static boolean isAlive(Player player) {
		if (isInArena(player) == false) {
			return false;
		}
		PlayerArenaStatus status = getStatusMetadata(player);
		return status.isPlayerAlive();
	}

	public static boolean isTagged(Player player) {
		if (isAlive(player) == false) {
			return false;
		}
		PlayerArenaStatus status = getStatusMetadata(player);
		if (status.isTagged() == false) {
			return false;
		}
		return true;
	}

	private static PlayerArenaStatus getStatusMetadata(Player player) {
		MetadataValue data = player.getMetadata("HotPotatoStatus").get(0);
		if (checkForMetaError(data) == false) {
			Bukkit.getLogger().log(Level.SEVERE, "Error grabbing Metadata for player " + player.getDisplayName() + "!");
			Bukkit.getLogger().log(Level.INFO,
					"Please send a copy of the log going back to when the Hot Potato game started to"
							+ "Xfur on Spigot so that he can fix this problem! I'm sorry for the inconvienence!");
			// TODO Remove player from the game when they throw a metaData error.
		}
		PlayerArenaStatus status = (PlayerArenaStatus) data.value();
		return status;
	}

	private static boolean checkForMetaError(MetadataValue data) {
		if (!(data.value() instanceof PlayerArenaStatus)) {
			return false;
		}
		return true;
	}

}