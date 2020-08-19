package me.CloverCola.HotPotato;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.DataClasses.InventoryStatusObject;
import me.CloverCola.HotPotato.DataClasses.PlayerArenaStatus;
import me.CloverCola.HotPotato.TaggedPlayer.TaggedFireworks;
import me.CloverCola.HotPotato.TaggedPlayer.TaggedPlayerItems;

public class StatusCheck {
	
	static private HashMap<Player, PlayerArenaStatus> playerStatus = new HashMap<Player, PlayerArenaStatus>();

	public StatusCheck() {
		
	}

	public void joinArenaStatus(Player player, PlayerArenaStatus status) {
		playerStatus.put(player, status);
		storePlayerInventory(player);
	}
	
	private void storePlayerInventory(Player player) {
		InventoryStatusObject inv = new InventoryStatusObject(player);
		PlayerInventoryStorage storage = new PlayerInventoryStorage();
		storage.storeInventory(player, inv);
	}

	public void leaveArenaStatus(Player player) {
		if (isInArena(player) == false) {
			return;
		}
		playerStatus.remove(player);
		PlayerInventoryStorage storage = new PlayerInventoryStorage();
		storage.retrieveInventory(player);
	}
	
	public boolean isInArena(Player player) {
		if (!playerStatus.containsKey(player)) {
			return false;
		}
		return true;
	}
	
	public void clearArena() {
		if (playerStatus.size() == 0) {
			return;
		}
		for (Map.Entry<Player, PlayerArenaStatus> entry : playerStatus.entrySet()) {
			leaveArenaStatus(entry.getKey());
		}
	}
	
	public boolean checkIfTagged(Player player) {
		if (playerStatus.containsKey(player) == false) {
			return false;
		}
		if (playerStatus.get(player).isTagged() == false) {
			return false;
		}
		return true;
	}
	
	/**
	 * NOTE: If switching Tagged Player from one player to another, do NOT use this method.
	 * @param player
	 * @return true if the player is in an arena. False otherwise.
	 */
	public boolean setTaggedPlayer(Player player) {
		if (!playerStatus.containsKey(player)) {
			return false;
		}
		playerStatus.get(player).setTagged(true);
		activatePotato(player);
		return true;
	}
	
	private void activatePotato(Player player) {
		TaggedPlayerItems items = new TaggedPlayerItems();
		items.equipItems(player);
		TaggedFireworks fireworks = new TaggedFireworks();
		fireworks.fireworksTimer(player);
	}
	
}
