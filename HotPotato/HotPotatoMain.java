package me.CloverCola.HotPotato;
import org.bukkit.plugin.java.JavaPlugin;

import me.CloverCola.HotPotato.Commands.Commands;
import me.CloverCola.HotPotato.ConfigUtilities.LocationFileManager;
import me.CloverCola.HotPotato.GameMechanics.PotatoPass;
import me.CloverCola.HotPotato.GameMechanics.PotatoTimer;
import me.CloverCola.HotPotato.GameMechanics.TaggedPlayer.TaggedFireworks;
import me.CloverCola.HotPotato.SafetyEvents.PlayerQuit;
import me.CloverCola.HotPotato.SafetyEvents.PreventFireworkDamage;
import me.CloverCola.HotPotato.SafetyEvents.PreventItemMovement;

public class HotPotatoMain extends JavaPlugin{
	
	public static HotPotatoMain plugin;

	@Override
	public void onEnable() {
		plugin = this;
		getCommand("hotPotato").setExecutor(new Commands());
		this.getServer().getPluginManager().registerEvents(new PreventItemMovement(), this);
		this.getServer().getPluginManager().registerEvents(new PotatoTimer(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		this.getServer().getPluginManager().registerEvents(new TaggedFireworks(), this);
		this.getServer().getPluginManager().registerEvents(new PreventFireworkDamage(), this);
		this.getServer().getPluginManager().registerEvents(new PotatoPass(), this);
		this.saveConfig();
		LocationFileManager initialize = new LocationFileManager();
		initialize.saveConfig();
		}
	
	@Override
	public void onDisable() {
		StatusManager.emptyAllArenas();
		PotatoTimer.shutDownBossBars();
	}
	
	public static HotPotatoMain getInstance() {
		return plugin;
	}
	
	
}
