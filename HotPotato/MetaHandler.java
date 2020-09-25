package me.CloverCola.HotPotato;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import me.CloverCola.HotPotato.DataClasses.PlayerArenaStatus;

//Handles all misc. metadata functions not related to a specific class.
public class MetaHandler {

	public static void setJoinedMeta(Player player, String arenaName) {
		PlayerArenaStatus status = new PlayerArenaStatus(player, arenaName);
		FixedMetadataValue data = new FixedMetadataValue(HotPotatoMain.getInstance(), status);
		player.setMetadata("HotPotatoStatus", data);
		return;
	}

	public static boolean inArena(Player player) {
		if (player.hasMetadata("HotPotatoStatus") == false) {
			return false;
		}
		return true;
	}
	
	public static String getArena(Player player) {
		PlayerArenaStatus status = getStatusMetadata(player);
		return status.getArenaName();
	}

	public static boolean isAlive(Player player) {
		if (inArena(player) == false) {
			return false;
		}
		PlayerArenaStatus status = getStatusMetadata(player);
		return status.isPlayerAlive();
	}

	public static boolean isTagged(Player player) {
		if (isAlive(player) == false) {
			return false;
		}
		PlayerArenaStatus status = getStatusMetadata(player);
		if (status.isTagged() == false) {
			return false;
		}
		return true;
	}

	// This method assumes you have checked if the player is in an arena first.
	public static void setTagged(Player player, boolean tagged) {
		PlayerArenaStatus status = getStatusMetadata(player);
		status.setTagged(tagged);
		if (tagged == true) {
			StatusManager.setTaggedInArena(player);
		}
		return;
	}
	
	public static void removeMetadata(Player player) {
		player.removeMetadata("HotPotatoStatus", HotPotatoMain.getInstance());
	}

	private static PlayerArenaStatus getStatusMetadata(Player player) {
		MetadataValue data = player.getMetadata("HotPotatoStatus").get(0);
		if (checkForMetaError(data) == false) {
			emergencyError(player);
			return null;
		}
		PlayerArenaStatus status = (PlayerArenaStatus) data.value();
		return status;
	}

	private static void emergencyError(Player player) {
		Bukkit.getLogger().log(Level.SEVERE, "Error grabbing Metadata for player " + player.getDisplayName() + "!");
		Bukkit.getLogger().log(Level.INFO,
				"Kicking " + player.getDisplayName() + " from the HotPotato game to prevent errors!");
		player.sendMessage(ChatColor.DARK_RED + "A serious error occured, and you have been kicked from the game!");
		StatusManager.leave(player);
		return;
	}

	private static boolean checkForMetaError(MetadataValue data) {
		if (!(data.value() instanceof PlayerArenaStatus)) {
			return false;
		}
		return true;
	}

}
