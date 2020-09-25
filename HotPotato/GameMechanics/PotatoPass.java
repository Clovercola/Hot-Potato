package me.CloverCola.HotPotato.GameMechanics;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.CloverCola.HotPotato.MetaHandler;
import me.CloverCola.HotPotato.GameMechanics.TaggedPlayer.TaggedHub;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class PotatoPass implements Listener {

	@EventHandler
	public void playerTagEvent(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player && event.getEntity() instanceof Player)) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		if (checkIfValid(victim, damager) == false) {
			return;
		}
		TaggedHub.activate(victim);
		TaggedHub.disarm(damager);
		sendPassedMessage(damager, victim);
		return;
	}

	private boolean checkIfValid(Player victim, Player damager) {
		if (MetaHandler.isAlive(victim) == false) {
			return false;
		}
		if (MetaHandler.isTagged(damager) == false) {
			return false;
		}
		return true;
	}

	private void sendPassedMessage(Player oldTagged, Player newTagged) {
		passedOnPotato(oldTagged);
		gotPotato(newTagged);
	}
	
	public static void passedOnPotato(Player oldTagged) {
		String passed = ChatColor.GOLD + "You passed the potato!";
		oldTagged.sendMessage(passed);
		BaseComponent[] comp = TextComponent.fromLegacyText(passed, ChatColor.GOLD);
		oldTagged.spigot().sendMessage(ChatMessageType.ACTION_BAR, comp);
		oldTagged.sendTitle(null, null, 0, 0, 0);
		return;
	}
	
	public static void gotPotato(Player newTagged) {
		String warn = ChatColor.RED + "You have the potato!";
		newTagged.sendMessage(warn);
		newTagged.sendTitle(warn, "", 10, 60, 20);
		BaseComponent[] taggedComp = TextComponent.fromLegacyText(warn, ChatColor.DARK_RED);
		newTagged.spigot().sendMessage(ChatMessageType.ACTION_BAR, taggedComp);
	}
	
}
