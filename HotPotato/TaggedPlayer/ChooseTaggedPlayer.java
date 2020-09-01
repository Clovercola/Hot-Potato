package me.CloverCola.HotPotato.TaggedPlayer;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import me.CloverCola.HotPotato.StatusCheck;
import me.CloverCola.HotPotato.DataClasses.PlayerArenaStatus;

public class ChooseTaggedPlayer {
	
	public void randomTaggedPlayer(Player player, String arenaName) {
		int count = StatusCheck.getPlayerCount(arenaName);
		int tagSlot = generateRandomNumber(count);
		StatusCheck.getPlayerFromArena(arenaName, tagSlot);
		MetadataValue meta = player.getMetadata("HotPotatoStatus").get(0);
		//TODO safety instance check
		PlayerArenaStatus status = (PlayerArenaStatus) meta.value();
		status.setTagged(true);
		return;
	}
	
	private int generateRandomNumber(int count) {
		Random random = new Random();
		return random.nextInt(count);
	}
	
}
