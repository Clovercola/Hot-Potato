package me.CloverCola.HotPotato.GameMechanics.TaggedPlayer;

import java.util.Random;

import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusManager;
import me.CloverCola.HotPotato.GameMechanics.PotatoPass;

public class ChooseTaggedPlayer {

	public static void randomTaggedPlayer(String arenaName) {
		int count = StatusManager.getPlayerCount(arenaName);
		int tagSlot = generateRandomNumber(count);
		Player player = StatusManager.getPlayerFromArena(arenaName, tagSlot);
		TaggedHub.activate(player);
		PotatoPass.gotPotato(player);
		return;
	}

	private static int generateRandomNumber(int count) {
		Random random = new Random();
		return random.nextInt(count);
	}

}
