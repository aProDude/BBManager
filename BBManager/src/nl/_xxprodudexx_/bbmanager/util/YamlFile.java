package nl._xxprodudexx_.bbmanager.util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl._xxprodudexx_.bbmanager.Main;

public class YamlFile {

	private YamlFile() {
	}

	private File file;
	private FileConfiguration config;
	
	private static YamlFile playerDataFile = new YamlFile("playerdata");
	
	public static YamlFile getPlayerDataFile(){
		return playerDataFile;
	}

	/**
	 * This method generates a new yml-file. The name can be set using the constructor-paramter 'String fileName'
	 * @param fileName ^^
	 */
	public YamlFile(String fileName) {
		if (!Main.getInstance().getDataFolder().exists()) {
			Main.getInstance().getDataFolder().mkdirs();
		}

		File dataFolder = Main.getInstance().getDataFolder();
		file = new File(dataFolder, fileName + ".yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		config = YamlConfiguration.loadConfiguration(file);
	}
	
	/**
	 * Load the configuration file and catch any possible exceptions
	 */

	public void load() {
		try {
			config.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save the configuration file and catch any possible exceptions
	 */

	public void save() {
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reload the configuration file
	 */

	public void reload() {
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	/**
	 * Returns the FileConfiguration
	 */

	public FileConfiguration getConfig(){
		return config;
	}
}
