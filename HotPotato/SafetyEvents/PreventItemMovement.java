package me.CloverCola.HotPotato.SafetyEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import me.CloverCola.HotPotato.MetaHandler;

public class PreventItemMovement implements Listener {

	@EventHandler
	public void clickingInventoryItem(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (MetaHandler.inArena(player) == false) {
			return;
		}
		event.setCancelled(true);
	}

	@EventHandler
	public void swapHandsEvent(PlayerSwapHandItemsEvent event) {
		Player player = (Player) event.getPlayer();
		if (MetaHandler.inArena(player) == false) {
			return;
		}
		event.setCancelled(true);
	}

}
