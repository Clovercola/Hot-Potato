package me.CloverCola.HotPotato;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class PotatoTimer implements Listener {
	
	private static HotPotatoMain plugin;

	public PotatoTimer() {
		// Empty Constructor
	}
	
	public PotatoTimer(HotPotatoMain instance) {
		plugin = instance;
	}

	public void activateTimer(Player player) {
		BossBar timerBar = Bukkit.createBossBar("Test Bar", BarColor.RED, BarStyle.SOLID);
		timerBar.addPlayer(player);
		new BukkitRunnable() {
			int countDown = 30;
			double progress = 1.0;
			double lower = 1.0 / countDown;
			@Override
			public void run() {
				if (countDown == 0) {
					player.sendMessage("The potato has exploded!");
					timerBar.setProgress(0.0);
					timerBar.removeAll();
					cancel();
				}
				else {
					player.sendMessage("Time left: " + countDown);
					countDown -= 1;
					progress -= lower;
					timerBar.setProgress(progress);
				}
			}//End of run
		}.runTaskTimer(plugin, 0, 20);
	}//End of method
}
