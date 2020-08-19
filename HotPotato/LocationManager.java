package me.CloverCola.HotPotato;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationManager {

	private static HotPotatoMain plugin = null;
	private FileConfiguration dataConfig = null;
	private File configFile = null;
	
	/*
	 * This constructor is meant to be used by ALL classes outside of the main class.
	 * DO NOT use the plugin instance constructor outside the main class.
	 */
	public LocationManager() {
		saveConfig();
	}
	
	//Only meant to be used in the main class to pass the plugin instance.
	protected LocationManager(HotPotatoMain instance) {
		LocationManager.plugin = instance;
	}

	public void reloadConfig() {
		if (this.configFile == null) {
			this.configFile = new File(LocationManager.plugin.getDataFolder(), "locations.yml");
		}
		this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
		InputStream defaultStream = LocationManager.plugin.getResource("locations.yml");
		if (defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.dataConfig.setDefaults(defaultConfig);
		}
	}

	public FileConfiguration getConfig() {
		if (this.dataConfig == null) {
			reloadConfig();
		}
		return this.dataConfig;
	}

	public void saveConfig() {
		if (this.dataConfig == null || this.configFile == null) {
			return;
		}
		try {
			this.getConfig().save(this.configFile);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE,
					"Could not save Locations file! Changes have NOT been saved!");
		}
	}

	public void saveDefaultConfig() {
		if (this.configFile == null) {
			this.configFile = new File(LocationManager.plugin.getDataFolder(), "locations.yml");
		}

		if (!this.configFile.exists()) {
			LocationManager.plugin.saveResource("locations.yml", false);
		}
	}

}
