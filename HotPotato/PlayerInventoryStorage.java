package me.CloverCola.HotPotato;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import me.CloverCola.HotPotato.DataClasses.InventoryStatusObject;

public class PlayerInventoryStorage {
	
	private static HashMap<Player, InventoryStatusObject> storage = new HashMap<Player, InventoryStatusObject>();
	
	public PlayerInventoryStorage() {
	}
	
	public void storeInventory(Player player, InventoryStatusObject inv) {
		storage.put(player, inv);
		clearPlayer(player);
		return;
	}
	
	private void clearPlayer(Player player) {
		player.getInventory().clear();
		player.setLevel(0);
		player.setExp(0.0f);
		player.setFoodLevel(20);
		player.setSaturation(20.0f);
		player.setHealth(20.0);
		player.setHealthScaled(false);
		for (PotionEffect effect : player.getActivePotionEffects()) {
			player.removePotionEffect(effect.getType());
		}
		return;
	}
	
	public void retrieveInventory(Player player) {
		clearPlayer(player);
		InventoryStatusObject status = storage.get(player);
		player.getInventory().setContents(status.getItems());
		player.setLevel(status.getPlayerLevel());
		player.setExp(status.getExpPoints());
		player.setFoodLevel(status.getPlayerFoodLevel());
		player.setSaturation(status.getSaturationLevel());
		player.setHealth(status.getHealthLevel());
		player.setHealthScaled(status.isPlayerHealthScaled());
		player.addPotionEffects(status.getPlayerEffects());
		return;
	}
	
}
