package me.CloverCola.HotPotato;
import org.bukkit.plugin.java.JavaPlugin;

import me.CloverCola.HotPotato.Commands.Commands;
import me.CloverCola.HotPotato.ConfigUtilities.LocationManager;
import me.CloverCola.HotPotato.SafetyEvents.PlayerQuit;
import me.CloverCola.HotPotato.SafetyEvents.PreventFireworkDamage;
import me.CloverCola.HotPotato.SafetyEvents.PreventItemMovement;
import me.CloverCola.HotPotato.TaggedPlayer.PotatoPass;
import me.CloverCola.HotPotato.TaggedPlayer.PotatoTimer;
import me.CloverCola.HotPotato.TaggedPlayer.TaggedFireworks;

public class HotPotatoMain extends JavaPlugin{
	
	public static HotPotatoMain plugin;

	@Override
	public void onEnable() {
		plugin = this;
		getCommand("hotPotato").setExecutor(new Commands());
		this.getServer().getPluginManager().registerEvents(new PreventItemMovement(), this);
		this.getServer().getPluginManager().registerEvents(new PotatoTimer(plugin), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		this.getServer().getPluginManager().registerEvents(new TaggedFireworks(plugin), this);
		this.getServer().getPluginManager().registerEvents(new PreventFireworkDamage(), this);
		this.getServer().getPluginManager().registerEvents(new PotatoPass(), this);
		this.saveConfig();
		LocationManager initialize = new LocationManager(plugin);
		initialize.saveConfig();
		PlayerInventoryStorage psInit = new PlayerInventoryStorage();
		psInit.setPluginInstance(plugin);
		StatusCheck.getPluginInstance(plugin);
		StartArena.setPluginInstance(plugin);
		}
	
	@Override
	public void onDisable() {
		StatusCheck.emptyAllArenas();
	}
	
	public static HotPotatoMain getInstance() {
		return plugin;
	}
	
	
}
