package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand {

	public HelpCommand() {
		
	}
	
	public void hotPotatoHelp(Player player) {
		player.sendMessage(ChatColor.GOLD + "-----------------------------------------------");
		player.sendMessage(ChatColor.GREEN + "/hotpotato help: displays this message");
		player.sendMessage(ChatColor.GOLD + "-----------------------------------------------");
	}
	
}
