package me.CloverCola.HotPotato.TaggedPlayer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TaggedPlayerItems {

	public TaggedPlayerItems() {
		
	}
	
	public void equipItems(Player player) {
		player.getInventory().setItemInOffHand(customPotato());
		player.getInventory().setHelmet(tntHelmet());
	}

	private ItemStack tntHelmet() {
		ItemStack tnt = new ItemStack(Material.TNT);
		ItemMeta meta = tnt.getItemMeta();
		String name = ChatColor.RESET + "" + ChatColor.RED + "Potato Timer";
		meta.setDisplayName(name);
		List<String> lore = new ArrayList<String>();
		String lineOne = ChatColor.RESET + "" + ChatColor.GRAY + "If you're reading this,";
		String lineTwo = ChatColor.RESET + "" + ChatColor.GRAY + "you should probably be";
		String lineThree = ChatColor.RESET + "" + ChatColor.GRAY + "passing the " + ChatColor.BOLD + ChatColor.RED
				+ "Hot Potato!";
		lore.add(lineOne);
		lore.add(lineTwo);
		lore.add(lineThree);
		meta.setLore(lore);
		tnt.setItemMeta(meta);
		return tnt;
	}
	
	private ItemStack customPotato() {
		ItemStack potato = new ItemStack(Material.BAKED_POTATO);
		ItemMeta meta = potato.getItemMeta();
		String name = ChatColor.RESET + "" + ChatColor.RED + ChatColor.BOLD + "Hot Potato";
		meta.setDisplayName(name);
		List<String> lore = new ArrayList<String>();
		String lineOne = ChatColor.RESET + "" + ChatColor.GRAY + "A " + ChatColor.BOLD + "SUPER HOT " + ChatColor.RESET
				+ ChatColor.GRAY + "Potato!";
		String lineTwo = ChatColor.RESET + "" + ChatColor.GRAY + "Hit somebody to pass the potato!";
		lore.add(lineOne);
		lore.add(lineTwo);
		meta.setLore(lore);
		potato.setItemMeta(meta);
		return potato;
	}

}
