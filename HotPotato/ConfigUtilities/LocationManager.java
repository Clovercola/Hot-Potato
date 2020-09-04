package me.CloverCola.HotPotato.ConfigUtilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.CloverCola.HotPotato.HotPotatoMain;

public class LocationManager {

	private FileConfiguration dataConfig = null;
	private File configFile = null;
	
	public LocationManager() {
		saveConfig();
	}

	public void reloadConfig() {
		if (this.configFile == null) {
			this.configFile = new File(HotPotatoMain.getPlugin().getDataFolder(), "locations.yml");
		}
		this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
		InputStream defaultStream = HotPotatoMain.getPlugin().getResource("locations.yml");
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
			HotPotatoMain.getPlugin().getLogger().log(Level.SEVERE,
					"Could not save Locations file! Changes have NOT been saved!");
		}
	}

	public void saveDefaultConfig() {
		if (this.configFile == null) {
			this.configFile = new File(HotPotatoMain.getPlugin().getDataFolder(), "locations.yml");
		}
		if (!this.configFile.exists()) {
			HotPotatoMain.getPlugin().saveResource("locations.yml", false);
		}
	}

}
