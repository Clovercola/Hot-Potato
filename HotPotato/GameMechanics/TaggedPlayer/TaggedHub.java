package me.CloverCola.HotPotato.GameMechanics.TaggedPlayer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.CloverCola.HotPotato.MetaHandler;
import me.CloverCola.HotPotato.StatusManager;

/**
 * A simple class meant to connect other "TaggedPlayer" classes together. Use
 * once a tagged player has been determined.
 * 
 * @author CloverCola
 *
 */
public class TaggedHub {

	public static void activate(Player player) {
		MetaHandler.setTagged(player, true);
		StatusManager.setTaggedInArena(player);
		TaggedPlayerItems.equipItems(player);
		TaggedFireworks.fireworksTimer(player);
		TaggedPlayerEffects.applySpeed(player);
		return;
	}
	
	public static void disarm(Player player) {
		MetaHandler.setTagged(player, false);
		ItemStack air = new ItemStack(Material.AIR);
		TaggedPlayerEffects.removeEffects(player);
		player.getInventory().setHelmet(air);
		player.getInventory().setItemInOffHand(air);
		return;
	}

}
