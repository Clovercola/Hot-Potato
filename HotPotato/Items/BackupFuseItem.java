package me.CloverCola.HotPotato.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.CloverCola.HotPotato.DataClasses.TimerData;
import me.CloverCola.HotPotato.GameMechanics.PotatoTimer;

public class BackupFuseItem {

	public BackupFuseItem() {

	}

	public static ItemStack create() {
		ItemStack item = new ItemStack(Material.STRING);
		ItemMeta meta = metaCreator(item);
		item.setItemMeta(meta);
		return item;
	}

	private static ItemMeta metaCreator(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Backup Fuse");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Right-click to use!");
		lore.add(ChatColor.GOLD + "Adds 5 seconds to the");
		lore.add(ChatColor.GOLD + "fuse!");
		meta.setLore(lore);
		return meta;
		}

	public static void activate(String arenaName) {
		TimerData data = PotatoTimer.getTimerData(arenaName);
		//Currently always "30" since config is not set up yet.
		int time = data.getTime();
		if (time + 5 > 30) {
			data.setTime(30);
		}
		else {
			data.setTime(time + 5);
		}
		return;
	}

}
