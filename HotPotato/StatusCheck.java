package me.CloverCola.HotPotato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import me.CloverCola.HotPotato.Commands.LobbyCommand;
import me.CloverCola.HotPotato.DataClasses.ArenaStatus;
import me.CloverCola.HotPotato.DataClasses.InventoryStatusObject;
import me.CloverCola.HotPotato.DataClasses.PlayerArenaStatus;

public class StatusCheck {

	private static HashMap<String, ArenaStatus> arenaList = new HashMap<String, ArenaStatus>();

	public StatusCheck() {

	}

	public static int getPlayerCount(String arenaName) {
		return arenaList.get(arenaName).getWaitingPlayers().size();
	}

	public static Player getPlayerFromArena(String arenaName, int slot) {
		return arenaList.get(arenaName).getWaitingPlayers().get(slot);
	}
	
	public static ArrayList<Player> getAllPlayersFromArena(String arenaName) {
		return arenaList.get(arenaName).getWaitingPlayers();
	}
	
	public static void emptyAllArenas() {
		for (String arenaName : arenaList.keySet()) {
			removeAllPlayersFromArena(arenaName);
		}
	}
	
	public static void removeAllPlayersFromArena(String arenaName) {
		ArrayList<Player> playerList = StatusCheck.getAllPlayersFromArena(arenaName);
		for (int i = 0; i < playerList.size(); i++) {
			Bukkit.getLogger().log(Level.INFO, playerList.get(i).getDisplayName());
			Bukkit.getLogger().log(Level.INFO, "PlayerList size: " + playerList.size());
			Bukkit.getLogger().log(Level.INFO, "Index: " + i);
			leave(playerList.get(i));
		}
		while (playerList.isEmpty() == false) {
			leave(playerList.get(0));
		}
		return;
	}
	
	public static boolean hasStarted(String arenaName) {
		if (arenaList.containsKey(arenaName) == false) {
			return false;
		}
		return arenaList.get(arenaName).hasStarted();
	}
	
	public static void setStarted(String arenaName, boolean status) {
		arenaList.get(arenaName).setStarted(status);
		return;
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
		StartArena.checkIfCanStart(arenaName);
		return;
	}

	private static void storeInventory(Player player) {
		InventoryStatusObject inv = new InventoryStatusObject(player);
		PlayerInventoryStorage.storeInventory(player, inv);
		return;
	}

	private static void setJoinedMeta(Player player, String arenaName) {
		PlayerArenaStatus status = new PlayerArenaStatus(player, arenaName);
		FixedMetadataValue data = new FixedMetadataValue(HotPotatoMain.getPlugin(), status);
		player.setMetadata("HotPotatoStatus", data);
		return;
	}

	public static void leave(Player player) {
		if (isInArena(player) == false) {
			player.sendMessage(ChatColor.GREEN + "You're not in a game!");
			return;
		}
		retrieveInventory(player);
		PlayerArenaStatus status = (PlayerArenaStatus) player.getMetadata("HotPotatoStatus").get(0).value();
		String arenaName = status.getArena();
		arenaList.get(arenaName).getWaitingPlayers().remove(player);
		player.removeMetadata("HotPotatoStatus", HotPotatoMain.getPlugin());
		LobbyCommand.teleportToLobby(player);
		WinCondition.check(arenaName);
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
	
	//This method assumes you have checked if the player is in an arena first.
	public static void setTagged(Player player, boolean tagged) {
		PlayerArenaStatus status = getStatusMetadata(player);
		status.setTagged(tagged);
	}

	private static PlayerArenaStatus getStatusMetadata(Player player) {
		MetadataValue data = player.getMetadata("HotPotatoStatus").get(0);
		if (checkForMetaError(data) == false) {
			Bukkit.getLogger().log(Level.SEVERE, "Error grabbing Metadata for player " + player.getDisplayName() + "!");
			Bukkit.getLogger().log(Level.INFO,
					"Please send a copy of the log going back to when the Hot Potato game started to"
							+ "Xfur on Spigot so that he can fix this problem! I'm sorry for the inconvienence!");
			// TODO Remove player from the game when they throw a metaData error.
			// TODO Actually do something if this method returns false
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