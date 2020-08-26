package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.ConfigUtilities.ArenaExistance;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;

public class ItemCommand {

	public ItemCommand() {

	}

	public void checkItemCommand(Player player, String[] args) {
		// hotpotato item create/delete <ItemSpawnName> <Arena>
		if (args.length == 4) {
			String check = args[1];
			LocationManager manager = new LocationManager();
			String name = args[3];
			if (ArenaExistance.doesArenaExist(manager, args[3]) == false) {
				ArenaExistance.warnMissingArena(player, name);
				return;
			}
			if (check.equalsIgnoreCase("CREATE")) {
				createItemSpawn(manager, player, name);
				return;
			}
			if (check.equalsIgnoreCase("DELETE")) {
				deleteItemSpawn(manager, player, name);
				return;
			}
			player.sendMessage(ChatColor.RED + "Usage: /hotpotato item <create/delete> <Item Spawn Name> <Arena>");
			return;
		}
		return;
	}

	private void createItemSpawn(LocationManager manager, Player player, String name) {
		// TODO
		player.sendMessage("Items aren't implemented yet, and may not even be implemented AT ALL, so I'll fill this in"
				+ "if and when I decide we're gonna have items.");
	}

	private void deleteItemSpawn(LocationManager manager, Player player, String name) {
		// TODO
		player.sendMessage("Items aren't implemented yet, and may not even be implemented AT ALL, so I'll fill this in"
				+ "if and when I decide we're gonna have items.");
	}

}
