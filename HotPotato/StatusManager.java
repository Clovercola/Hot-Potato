package me.CloverCola.HotPotato;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.Commands.LobbyCommand;
import me.CloverCola.HotPotato.DataClasses.ArenaStatus;
import me.CloverCola.HotPotato.DataClasses.InventoryStatusObject;
import me.CloverCola.HotPotato.GameMechanics.StartArena;
import me.CloverCola.HotPotato.GameMechanics.WinCondition;

public class StatusManager {

	private static HashMap<String, ArenaStatus> arenaList = new HashMap<String, ArenaStatus>();

	public StatusManager() {

	}

	public static int getPlayerCount(String arenaName) {
		if (checkArenaCreated(arenaName) == false) {
			return 0;
		}
		return arenaList.get(arenaName).getWaitingPlayers().size();
	}

	public static Player getPlayerFromArena(String arenaName, int slot) {
		if (checkArenaCreated(arenaName) == false) {
			return null;
		}
		return arenaList.get(arenaName).getWaitingPlayers().get(slot);
	}

	public static ArrayList<Player> getAllPlayersFromArena(String arenaName) {
		if (checkArenaCreated(arenaName) == false) {
			return null;
		}
		return arenaList.get(arenaName).getWaitingPlayers();
	}

	public static void emptyAllArenas() {
		for (String arenaName : arenaList.keySet()) {
			removeAllPlayersFromArena(arenaName);
		}
	}

	public static void removeAllPlayersFromArena(String arenaName) {
		ArrayList<Player> playerList = StatusManager.getAllPlayersFromArena(arenaName);
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

	public static Player getTaggedFromArena(String arenaName) {
		return arenaList.get(arenaName).getTaggedPlayer();
	}

	public static void setTaggedInArena(Player player) {
		if (MetaHandler.inArena(player) == false) {
			//TODO kick player
		}
		String arenaName = MetaHandler.getArena(player);
		arenaList.get(arenaName).setTaggedPlayer(player);
		return;
	}
	
	public static void setTaggedInArena(String arenaName, Player player) {
		arenaList.get(arenaName).setTaggedPlayer(player);
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
		MetaHandler.setJoinedMeta(player, arenaName);
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

	public static void leave(Player player) {
		if (MetaHandler.inArena(player) == false) {
			player.sendMessage(ChatColor.GREEN + "You're not in a game!");
			return;
		}
		LobbyCommand.teleportToLobby(player);
		removePlayer(player);
		player.sendMessage(ChatColor.GREEN + "You have left the game!");
		return;
	}
	
	public static void removePlayer(Player player) {
		if (player == null) {
			return;
		}
		retrieveInventory(player);
		String arenaName = MetaHandler.getArena(player);
		arenaList.get(arenaName).getWaitingPlayers().remove(player);
		MetaHandler.removeMetadata(player);
		WinCondition.check(arenaName);
		return;
	}

	// This method exists as a compliment to the earlier "storeInventory" method.
	private static void retrieveInventory(Player player) {
		PlayerInventoryStorage.retrieveInventory(player);
		return;
	}

	public static boolean shutdownArena(String arenaName) {
		if (arenaList.remove(arenaName) == null) {
			return true;
		}
		return false;
	}
	
	private static boolean checkArenaCreated(String arenaName) {
		if (arenaList.containsKey(arenaName) == false) {
			return false;
		}
		return true;
	}

}