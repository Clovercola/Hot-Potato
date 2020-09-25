package me.CloverCola.HotPotato.SafetyEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.CloverCola.HotPotato.StatusManager;


public class PlayerQuit implements Listener{
	
	@EventHandler
	public void playerQuitServer(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		StatusManager.leave(player);
	}
	
}
