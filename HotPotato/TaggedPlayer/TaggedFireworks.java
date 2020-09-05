package me.CloverCola.HotPotato.TaggedPlayer;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.CloverCola.HotPotato.HotPotatoMain;
import me.CloverCola.HotPotato.StatusCheck;

public class TaggedFireworks implements Listener {
	
	public TaggedFireworks() {
		
	}
	
	private static Firework spawnFireworks(Player player) {
		Location loc = player.getLocation();
		loc.add(0, 2, 0);
		Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkEffect effect = buildFirework();
		FireworkMeta meta = firework.getFireworkMeta();
		meta.addEffect(effect);
		firework.setFireworkMeta(meta);
		return firework;
	}
	
	public static void fireworksTimer(Player player) {
		if (StatusCheck.isTagged(player) == false) {
			return;
		}
		new BukkitRunnable(){
			@Override
			public void run() {
				if (StatusCheck.isTagged(player) == false) {
					cancel();
				}
				Firework firework = spawnFireworks(player);
				firework.detonate();
			}
		}.runTaskTimer(HotPotatoMain.getPlugin(), 20, 20);
	}
	
	private static FireworkEffect buildFirework() {
		Color red = Color.RED;
		FireworkEffect builtFirework = FireworkEffect.builder().withColor(red).build();
		return builtFirework;
	}
	
	
}
