package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand {

	public HelpCommand() {

	}

	public static void hotPotatoHelp(Player player) {
		player.sendMessage(ChatColor.GREEN + "-------------[Hot Potato Commands]-------------");
		player.sendMessage(ChatColor.GOLD + "/hotpotato help: Displays this message.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato create: Creates a new arena.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato delete: Deletes a Hot Potato arena.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato disable: Disables a Hot Potato arena.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato enable: Enables a Hot Potato arena.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato entrance: Sets the entrance for a Hot Potato arena.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato item: Currently disabled");
		player.sendMessage(ChatColor.GOLD + "/hotpotato join: Join a Hot Potato arena.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato leave: Leave a Hot Potato arena.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato lobby: Teleports the player to the lobby.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato set: Sets the Hot Potato lobby.");
		player.sendMessage(ChatColor.GOLD + "/hotpotato spawn: Sets the spawn for the a Hot Potato arena.");
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------");
	}

}
