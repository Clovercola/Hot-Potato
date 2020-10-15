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

public class SmokescreenItem {
	
	public SmokescreenItem() {
		
	}
	
	public static ItemStack create() {
		ItemStack item = new ItemStack(Material.BLACK_DYE, 1);
		ItemMeta meta = metaCreator(item);
		item.setItemMeta(meta);
		return item;
		}
	
	private static ItemMeta metaCreator(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setLocalizedName(ChatColor.BLACK + "Smokescreen");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Right click to use!");
		lore.add(ChatColor.GRAY + "Inflicts blindness on everyone for 5 seconds!");
		meta.setLore(lore);
		return meta;
	}
	
	public static void activate(String arenaName) {
		Player player;
		PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 60, 0, false, false);
		for (int i = 0; i < StatusManager.getPlayerCount(arenaName); i++) {
			player = StatusManager.getPlayerFromArena(arenaName, i);
			if (MetaHandler.isAlive(player) == true) {
				player.addPotionEffect(blindness);
			}
		}
		return;
	}
	
	

}
