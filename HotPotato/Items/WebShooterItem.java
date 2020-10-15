package me.CloverCola.HotPotato.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.CloverCola.HotPotato.MetaHandler;
import me.CloverCola.HotPotato.StatusManager;

public class WebShooterItem {
	
	public WebShooterItem() {
		
	}
	
	public static ItemStack create() {
		ItemStack item = new ItemStack(Material.COBWEB);
		ItemMeta meta = metaCreator(item);
		item.setItemMeta(meta);
		return item;
	}

	private static ItemMeta metaCreator(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW + "Web Shooter");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Right-click to use.");
		lore.add(ChatColor.YELLOW + "Slows down everyone");
		lore.add(ChatColor.YELLOW + "but you for 5 seconds,");
		lore.add(ChatColor.YELLOW + "and makes you immune to");
		lore.add(ChatColor.YELLOW + "being slowed down for 5");
		lore.add(ChatColor.YELLOW + "seconds.");
		meta.setLore(lore);
		return meta;
		}

	public static void activate(String arenaName, Player user) {
		MetaHandler.setSlowImmune(user, true);
		Player player;
		PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, 100, 0, false, false);
		for (int i = 0; i < StatusManager.getPlayerCount(arenaName); i++) {
			player = StatusManager.getPlayerFromArena(arenaName, i);
			if (MetaHandler.isAlive(player) == true && MetaHandler.isSlowImmune(player) == false) {
				player.addPotionEffect(slowness);
			}
		}
		return;
	}

}
