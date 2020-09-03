package me.CloverCola.HotPotato.TaggedPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.CloverCola.HotPotato.StatusCheck;
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
		StatusCheck.setTagged(damager, false);
		StatusCheck.setTagged(victim, true);
		sendPassedMessage(damager, victim);
	}

	private boolean checkIfValid(Player victim, Player damager) {
		if (StatusCheck.isAlive(victim) == false) {
			return false;
		}
		if (StatusCheck.isTagged(damager) == false) {
			return false;
		}
		return true;
	}

	private void sendPassedMessage(Player oldTagged, Player newTagged) {
		String passed = ChatColor.GOLD + "You passed the potato!";
		String warn = ChatColor.RED + "You have the potato!";
		oldTagged.sendMessage(passed);
		BaseComponent[] comp = TextComponent.fromLegacyText(passed, ChatColor.GOLD);
		oldTagged.spigot().sendMessage(ChatMessageType.ACTION_BAR, comp);
		oldTagged.sendTitle(null, null, 0, 0, 0);
		newTagged.sendMessage(warn);
		newTagged.sendTitle(warn, "", 10, 60, 20);
		BaseComponent[] taggedComp = TextComponent.fromLegacyText(warn, ChatColor.DARK_RED);
		newTagged.spigot().sendMessage(ChatMessageType.ACTION_BAR, taggedComp);
	}
	
}