package me.CloverCola.HotPotato;
import org.bukkit.plugin.java.JavaPlugin;

import me.CloverCola.HotPotato.Commands.Commands;
import me.CloverCola.HotPotato.SafetyEvents.PlayerQuit;
import me.CloverCola.HotPotato.SafetyEvents.PreventItemMovement;
import me.CloverCola.HotPotato.TaggedPlayer.TaggedFireworks;

public class HotPotatoMain extends JavaPlugin{
	
	private final HotPotatoMain instance = this;

	@Override
	public void onEnable() {
		getCommand("hotPotato").setExecutor(new Commands());
		this.getServer().getPluginManager().registerEvents(new PreventItemMovement(), this);
		this.getServer().getPluginManager().registerEvents(new PotatoTimer(instance), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		this.getServer().getPluginManager().registerEvents(new TaggedFireworks(instance), this);
		this.saveConfig();
		LocationManager initialize = new LocationManager(instance);
		initialize.saveConfig();
		
	}
	
	@Override
	public void onDisable() {
		//TODO Fix the iterator so I can uncomment this.
		//StatusCheck remove = new StatusCheck();
		//remove.clearArena();
	}
	
}
