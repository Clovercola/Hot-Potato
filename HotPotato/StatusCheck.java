package me.CloverCola.HotPotato;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
		retrieveInventory(player);
		PlayerArenaStatus status = (PlayerArenaStatus) player.getMetadata("HotPotatoStatus").get(0).value();
		String arenaName = status.getArenaName();
		arenaList.get(arenaName).getWaitingPlayers().remove(player);
		player.removeMetadata("HotPotatoStatus", HotPotatoMain.getPlugin());
		LobbyCommand.teleportToLobby(player);
		WinCondition.check(arenaName);
		player.sendMessage(ChatColor.GREEN + "You have left the game!");
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

}