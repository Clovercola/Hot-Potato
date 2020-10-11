package me.CloverCola.HotPotato.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.CloverCola.HotPotato.DataClasses.TimerData;
import me.CloverCola.HotPotato.GameMechanics.PotatoTimer;

public class FuseCutterItem {
	
	public FuseCutterItem() {
		
	}

	public static ItemStack create() {
		ItemStack item = new ItemStack(Material.SHEARS);
		ItemMeta meta = metaCreator(item);
		item.setItemMeta(meta);
		return item;
	}

	private static ItemMeta metaCreator(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Fuse Cutter");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Right-click to use!");
		lore.add(ChatColor.GRAY + "Shortens the fuse by");
		lore.add(ChatColor.GRAY + "5 seconds! Will not");
		lore.add(ChatColor.GRAY + "work if there is less");
		lore.add(ChatColor.GRAY + "than 10 seconds left on");
		lore.add(ChatColor.GRAY + "the fuse.");
		meta.setLore(lore);
		return meta;
		}

	public static void activate(String arenaName) {
		TimerData data = PotatoTimer.getTimerData(arenaName);
		//Currently always "10" since config is not set up yet.
		int time = data.getTime();
		if (time - 5 < 10) {
			//Don't activate if timer is too low.
			return;
		}
		else {
			data.setTime(time - 5);
		}
		return;
	}
	
}
