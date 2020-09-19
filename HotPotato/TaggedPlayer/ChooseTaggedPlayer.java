package me.CloverCola.HotPotato.TaggedPlayer;

import java.util.Random;

import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusCheck;

public class ChooseTaggedPlayer {

	public static void randomTaggedPlayer(String arenaName) {
		int count = StatusCheck.getPlayerCount(arenaName);
		int tagSlot = generateRandomNumber(count);
		Player player = StatusCheck.getPlayerFromArena(arenaName, tagSlot);
		TaggedHub.activate(player, arenaName);
		return;
	}

	private static int generateRandomNumber(int count) {
		Random random = new Random();
		return random.nextInt(count);
	}

}
