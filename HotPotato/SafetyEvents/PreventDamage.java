package me.CloverCola.HotPotato.SafetyEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.CloverCola.HotPotato.MetaHandler;

public class PreventDamage implements Listener{

	@EventHandler
	public void prevent(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		if (MetaHandler.inArena(player) == false) {
			return;
		}
		event.setCancelled(true);
	}
	
}
