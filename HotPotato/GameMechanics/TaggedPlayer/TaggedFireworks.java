package me.CloverCola.HotPotato.GameMechanics.TaggedPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.CloverCola.HotPotato.MetaHandler;

public class TaggedFireworks implements Listener {
	
	public TaggedFireworks() {
		
	}
	/*
	 * 
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
	
	*/
	
	public static void fireworksTimer(Player player) {
		if (MetaHandler.isTagged(player) == false) {
			return;
		}
		
		//Temporarily disable
		
//		new BukkitRunnable(){
//			@Override
//			public void run() {
//				if (MetaHandler.isTagged(player) == false) {
//					cancel();
//				}
//				World world = player.getWorld();
//				world.spawnParticle(Particle.LAVA, player.getLocation().getX(), player.getLocation().getY() + 1,
//						player.getLocation().getZ(), 10);
				// Firework firework = spawnFireworks(player);
				// firework.detonate();
//			}
//		}.runTaskTimer(HotPotatoMain.getPlugin(), 20, 20);
	}
	
	/*
	private static FireworkEffect buildFirework() {
		Color red = Color.RED;
		FireworkEffect builtFirework = FireworkEffect.builder().withColor(red).build();
		return builtFirework;
	}
	*/
	
	
}
