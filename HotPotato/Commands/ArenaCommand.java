package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.LocationManager;
import me.CloverCola.HotPotato.StorageUtilities.LocationSerializationUtility;

public class ArenaCommand {

	public ArenaCommand() {

	}

	public void checkArenaCommand(Player player, String[] args) {
		if (args.length < 2) {
			player.sendMessage(ChatColor.RED + "Invalid syntax! Usage: /hotpotato arena create");
			return;
		}
		String check = args[1].toUpperCase();
		switch (check) {
		case "CREATE":
			if (args.length == 3) {
				createArena(player, args[2]);
			} else {
				player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena create <name>");
			}
			return;
		case "SET":
			setChoices(player, args);
			return;
		case "DELETE":
			if (args.length == 3) {
				deleteArena(player, args[2]);
			} else {
				player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena delete <name>");
			}
			return;
		case "ENABLE":
			if (args.length == 3) {
				enableArena(player, args[2]);
			}
			else {
				player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena enable <name>");
			}
			return;
		case "DISABLE":
			if (args.length == 3) {
				disableArena(player, args[2]);
			}
			else {
				player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena disable <name>");
			}
			return;
		default:
			// TODO
			return;
		}
	}

	private void setChoices(Player player, String[] args) {
		if (args.length < 4) {
			player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena set <Lobby | Spawn | Item> <Name>");
			return;
		}
		String check = args[2].toUpperCase();
		// TODO clean up location getting
		switch (check) {
		case "LOBBY":
			if (args.length == 4) {
				String locString = convertPlayerLoc(player);
				String name = args[3];
				setArenaLobby(player, name, locString);
			} else {
				player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena set lobby <Name>");
			}
			return;
		case "SPAWN":
			if (args.length == 4) {
				String name = args[3];
				setSpawn(player, name);
			} else {
				player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena set spawn <Name>");
			}
			return;
		case "ITEM":
			if (args.length == 5) {
				String name = args[3];
				String spawnName = args[4];
				setItemSpawn(player, name, spawnName);
			} else {
				player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena set item <Name> <Item Spawn Name>");
			}
			return;
		default:
			player.sendMessage(ChatColor.RED + "Usage: /hotpotato arena set <Lobby | Spawn | Item> <Name>");
		}
	}

	private void createArena(Player player, String name) {
		LocationManager lobby = new LocationManager();
		if (lobby.getConfig().contains("locations.arenas." + name) == true) {
			player.sendMessage(ChatColor.RED + "Error: Arena name " + name + " already exists!");
			return;
		}
		lobby.getConfig().set("locations.arenas." + name + ".enabled", false);
		lobby.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been created.");
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " is currently disabled. "
				+ "Do /hotpotato setLobby <arena> to set the arena's lobby.");
	}

	private void deleteArena(Player player, String name) {
		LocationManager arena = new LocationManager();
		arena.getConfig().set("locations.arenas." + name, null);
		arena.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been deleted!");
	}

	private void setArenaLobby(Player player, String name, String loc) {
		LocationManager lobby = new LocationManager();
		if (lobby.getConfig().contains("locations.arenas." + name) == false) {
			player.sendMessage(ChatColor.RED + "That arena doesn't exist! Check the spelling and try again!");
			return;
		}
		try {
			lobby.getConfig().set("locations.arenas." + name + ".lobby", loc);
			lobby.saveConfig();
		} catch (Exception e) {
			player.sendMessage(ChatColor.DARK_RED + "There was an error setting the arena's lobby!");
		}
		player.sendMessage(ChatColor.GREEN + "Arena " + name + "'s lobby has been set!");
	}

	private void setSpawn(Player player, String name) {
		LocationManager spawn = new LocationManager();
		String locString = convertPlayerLoc(player);
		spawn.getConfig().set("locations.arenas." + name + ".spawn", locString);
		spawn.saveConfig();
		player.sendMessage(ChatColor.GREEN + "You have set the spawn for " + name + "!");
	}

	private void setItemSpawn(Player player, String name, String spawnName) {
		LocationManager itemSpawn = new LocationManager();
		String locString = convertPlayerLoc(player);
		itemSpawn.getConfig().set("locations.arenas." + name + ".itemSpawns." + spawnName, locString);
		itemSpawn.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Item spawn set successfully!");
	}
	
	public void disableArena(Player player, String name) {
		LocationManager disable = new LocationManager();
		if (disable.getConfig().getBoolean("locations.arenas." + name + ".enabled") == false) {
			player.sendMessage(ChatColor.RED + "That arena is already disabled!");
		}
		disable.getConfig().set("locations.arenas." + name + ".enabled", false);
		disable.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been disabled.");
		//TODO list command to re-enable arena
	}
	
	public void enableArena(Player player, String name) {
		LocationManager enable = new LocationManager();
		if (enable.getConfig().getBoolean("locations.arenas." + name + ".enabled") == true) {
			player.sendMessage(ChatColor.RED + "That arena is already enabled!");
		}
		enable.getConfig().set("locations.arenas." + name + ".enabled", true);
		enable.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Arena " + name + " has been enabled!");
	}

	private String convertPlayerLoc(Player player) {
		LocationSerializationUtility util = new LocationSerializationUtility();
		String locString = util.createLocationFromPlayer(player);
		return locString;
	}

}