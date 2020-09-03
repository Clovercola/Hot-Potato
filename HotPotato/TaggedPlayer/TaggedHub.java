package me.CloverCola.HotPotato.TaggedPlayer;

import org.bukkit.entity.Player;

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
	
	

}
