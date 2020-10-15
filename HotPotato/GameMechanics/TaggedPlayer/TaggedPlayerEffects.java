package me.CloverCola.HotPotato.GameMechanics.TaggedPlayer;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TaggedPlayerEffects {

	public static void applySpeed(Player player) {
		PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 72000, 0);
		player.addPotionEffect(effect);
		return;
	}
	
	public static void removeEffects(Player player) {
		player.removePotionEffect(PotionEffectType.SPEED);
		return;
	}
	
}
