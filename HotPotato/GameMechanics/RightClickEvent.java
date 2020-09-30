package me.CloverCola.HotPotato.GameMechanics;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.CloverCola.HotPotato.MetaHandler;
import me.CloverCola.HotPotato.Items.BackupFuseItem;
import me.CloverCola.HotPotato.Items.FuseCutterItem;
import me.CloverCola.HotPotato.Items.SmokescreenItem;
import me.CloverCola.HotPotato.Items.TrackerItem;
import me.CloverCola.HotPotato.Items.WebShooterItem;

public class RightClickEvent implements Listener {

	public void rightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (MetaHandler.inArena(player) == false) {
			return;
		}
		Material item = event.getMaterial();
		if (item == Material.AIR) {
			return;
		}
		check(player, item);
	}

	private void check(Player player, Material item) {
		if (MetaHandler.isAlive(player) == false) {
			// This will be where "Spectator" items are checked.
		} else {
			itemClicked(player, item);
		}
	}

	private void itemClicked(Player player, Material item) {
		String arenaName = getArena(player);
		// Switch statements don't work on Materials, according to Eclipse. So instead,
		// we have this if-else if block.
		if (item == Material.BLACK_DYE) {
			SmokescreenItem.activate(arenaName);
		} else if (item == Material.SHEARS) {
			FuseCutterItem.activate(arenaName);
		} else if (item == Material.STRING) {
			BackupFuseItem.activate(arenaName);
		} else if (item == Material.COMPASS) {
			TrackerItem.activate(arenaName, player);
		} else if (item == Material.COBWEB) {
			WebShooterItem.activate(arenaName, player);
		} else {
			return;
		}
	}

	private String getArena(Player player) {
		return MetaHandler.getArena(player);
	}

}
