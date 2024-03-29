package me.CloverCola.HotPotato.SafetyEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.CloverCola.HotPotato.MetaHandler;

public class PreventFireworkDamage implements Listener {

	@EventHandler
	public void cancelFireworkDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		if (event.getCause() != DamageCause.ENTITY_EXPLOSION) {
			return;
		}
		Player player = (Player) event.getEntity();
		if (MetaHandler.inArena(player) == false) {
			return;
		}
		event.setCancelled(true);
	}
	
}
