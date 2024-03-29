package me.CloverCola.HotPotato;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;

import me.CloverCola.HotPotato.DataClasses.InventoryStatusObject;

public class PlayerInventoryStorage {
	
	public PlayerInventoryStorage() {
		
	}
	
	public static void storeInventory(Player player, InventoryStatusObject inv) {
		FixedMetadataValue meta = new FixedMetadataValue(HotPotatoMain.getInstance(), inv);
		player.setMetadata("HotPotatoStoredInventory", meta);
		clearPlayer(player);
		return;
	}
	
	private static void clearPlayer(Player player) {
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

	public static void retrieveInventory(Player player) {
		clearPlayer(player);
		MetadataValue meta = player.getMetadata("HotPotatoStoredInventory").get(0);
		// Safety check
		if (!(meta.value() instanceof InventoryStatusObject)) {
			player.sendMessage(ChatColor.DARK_RED + "Error returning your inventory!");
			Bukkit.getLogger().log(Level.SEVERE,
					"Could not return " + player.getDisplayName() + "'s inventory after a Hot Potato game!");
			return;
		}
		InventoryStatusObject status = (InventoryStatusObject) meta.value();
		// Deletes the ISO metadata off the player.
		player.removeMetadata("HotPotatoStoredInventory", HotPotatoMain.getInstance());
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
