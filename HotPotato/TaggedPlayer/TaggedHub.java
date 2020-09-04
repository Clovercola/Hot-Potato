package me.CloverCola.HotPotato.TaggedPlayer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.CloverCola.HotPotato.StatusCheck;

/**
 * A simple class meant to connect other "TaggedPlayer" classes together. Use
 * once a tagged player has been determined.
 * 
 * @author seano
 *
 */
public class TaggedHub {

	public static void activate(Player player) {
		StatusCheck.setTagged(player, true);
		TaggedPlayerItems.equipItems(player);
		TaggedFireworks.fireworksTimer(player);
		return;
	}
	
	//Aside from being a cutesy name, this helps communicate what this method does.
	//I swear that's genuinely why I chose it.
	public static void disarm(Player player) {
		StatusCheck.setTagged(player, false);
		ItemStack air = new ItemStack(Material.AIR);
		player.getInventory().setHelmet(air);
		player.getInventory().setItemInOffHand(air);
		return;
	}

}