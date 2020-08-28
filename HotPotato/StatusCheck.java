package me.CloverCola.HotPotato;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import me.CloverCola.HotPotato.DataClasses.ArenaPlayers;
import me.CloverCola.HotPotato.DataClasses.InventoryStatusObject;
import me.CloverCola.HotPotato.DataClasses.PlayerArenaStatus;

public class StatusCheck {

	private static HotPotatoMain plugin;
	private static HashMap<String, ArenaPlayers> arenaStatus = new HashMap<String, ArenaPlayers>();

	public StatusCheck() {

	}

	public static void getPluginInstance(HotPotatoMain instance) {
		plugin = instance;
	}

	public static void joinArenaStatus(Player player, String arena) {
		int total = 1;
		// TODO make sure that the amount of players is equal to the number of stored
		// players.
		if (arenaStatus.containsKey(arena) == false || arenaStatus.get(arena).getCount() + 1 < 1) {
			ArenaPlayers initArena = new ArenaPlayers(1, player);
			arenaStatus.put(arena, initArena);
		} else {
			total = arenaStatus.get(arena).getCount() + 1;
			arenaStatus.get(arena).setCount(total);
		}
		storePlayerInventory(player);
		setJoinedArenaMeta(player, arena);
		if (total == 1) {
			player.sendMessage(ChatColor.GREEN + "There is now 1 player waiting!");
			return;
		}
		player.sendMessage(ChatColor.GREEN + "There are now " + total + " players waiting!");
	}

	public static void leaveArena(Player player) {
		if (player.hasMetadata("HotPotatoStatus") == false) {
			player.sendMessage(ChatColor.RED + "You aren't in a game!");
			return;
		}
		MetadataValue data = player.getMetadata("HotPotatoStatus").get(0);
		PlayerArenaStatus status = (PlayerArenaStatus) data.value();
		String arena = status.getArena();
		// Error catching
		if (arenaStatus.containsKey(arena) == false) {
			Bukkit.getLogger().log(Level.SEVERE,
					"Internal error with removing a player from a Hot Potato arena! Error code: 187");
		} else {
			int total = arenaStatus.get(arena).getCount() - 1;
			arenaStatus.get(arena).setCount(total);
		}
		player.removeMetadata("HotPotatoStatus", plugin);
		PlayerInventoryStorage.retrieveInventory(player);
	}

	public static boolean isInArena(Player player) {
		if (player.hasMetadata("HotPotatoStatus") == false) {
			return false;
		}
		return true;
	}

	public static boolean isAlive(Player player) {
		if (player.hasMetadata("HotPotatoStatus") == false) {
			return false;
		}
		MetadataValue data = player.getMetadata("HotPotatoStatus").get(0);
		// Safety check w/ Error code 317
		// Error codes will be put throughout eventually to mark errors to make
		// debugging easier.
		if (!(data.value() instanceof PlayerArenaStatus)) {
			Bukkit.getLogger().log(Level.WARNING, "Hot Potato threw an internal error! Error code: 317");
			return false;
		}
		PlayerArenaStatus status = (PlayerArenaStatus) data.value();
		if (status.isPlayerAlive() == true) {
			return true;
		}
		return false;
	}

	public static boolean isPlayerTagged(Player player) {
		if (isAlive(player) == false) {
			return false;
		}
		MetadataValue data = player.getMetadata("HotPotatoStatus").get(0);
		if (!(data.value() instanceof PlayerArenaStatus)) {
			Bukkit.getLogger().log(Level.WARNING, "Hot Potato threw an internal error! Error code: 329");
			return false;
		}
		PlayerArenaStatus status = (PlayerArenaStatus) data.value();
		if (status.isTagged() == true) {
			return true;
		}
		return false;
	}

	private static void storePlayerInventory(Player player) {
		InventoryStatusObject inv = new InventoryStatusObject(player);
		PlayerInventoryStorage.storeInventory(player, inv);
	}

	private static void setJoinedArenaMeta(Player player, String arena) {
		PlayerArenaStatus status = new PlayerArenaStatus(player, arena);
		FixedMetadataValue meta = new FixedMetadataValue(plugin, status);
		player.setMetadata("HotPotatoStatus", meta);
	}

}
