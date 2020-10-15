package me.CloverCola.HotPotato.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.CloverCola.HotPotato.MetaHandler;
import me.CloverCola.HotPotato.StatusManager;

public class TrackerItem {

	private static HashMap<Player, Long> cooldown = new HashMap<Player, Long>();

	public TrackerItem() {

	}

	public static ItemStack create() {
		ItemStack item = new ItemStack(Material.COMPASS, 1);
		ItemMeta meta = metaCreator(item);
		item.setItemMeta(meta);
		return item;
	}

	private static ItemMeta metaCreator(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Tracker");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Right-click to use!");
		lore.add(ChatColor.RED + "2 second cooldown.");
		lore.add(ChatColor.GRAY + "Points towards the tagged player.");
		meta.setLore(lore);
		return meta;
	}

	public static void activate(String arenaName, Player player) {
		if (StatusManager.getTaggedFromArena(arenaName) == null) {
			return;
		}
		// Since Player cannot be null, you have to check if the tagged player is
		// still actually alive.
		Player tagged = StatusManager.getTaggedFromArena(arenaName);
		if (MetaHandler.isAlive(player) == false) {
			return;
		}
		if (cooldown.containsKey(player) && cooldown.get(player) > System.currentTimeMillis()) {
			//Change how this works so it shows how many milliseconds there are.
			double timeLeft = ((cooldown.get(player) - System.currentTimeMillis())) / 1000;
			player.sendMessage(
					ChatColor.RED + "The tracker is still on cooldown for " + timeLeft + " seconds!");
			return;
		}
		player.setCompassTarget(tagged.getLocation());
		cooldown.put(player, System.currentTimeMillis() + 2000);
		return;
	}
}
