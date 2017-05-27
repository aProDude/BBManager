package nl._xxprodudexx_.bbmanager.util;

import java.io.File;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl._xxprodudexx_.bbmanager.Main;

public class YamlFile {

	private String name;
	private File file;
	private FileConfiguration config;

	/**
	 * This method generates a new yml-file. The name can be set using the
	 * constructor-paramter 'String fileName'
	 * 
	 * @param fileName
	 *            ^^
	 */
	public YamlFile(String fileName) {
		this.name = fileName;
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
		config.options().header("This is " + fileName + ".yml - file.");
		this.save();
	}

	public YamlFile(UUID uuid) {
		this.name = uuid.toString();
		if (!Main.getInstance().getDataFolder().exists()) {
			Main.getInstance().getDataFolder().mkdirs();
		}

		File dataFolder = Main.getInstance().getDataFolder();
		File folder = new File(dataFolder, "Data");

		if (!folder.exists()) {
			folder.mkdirs();
		}

		file = new File(folder, uuid + ".yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		config = YamlConfiguration.loadConfiguration(file);
		config.options().header("This is the datafile of " + uuid.toString() + ".");
		this.save();
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
	 * @return The name of the File
	 */

	public String getName() {
		return name;
	}

	/**
	 * @return The FileConfiguration
	 */

	public FileConfiguration getConfig() {
		return config;
	}

	/**
	 * @return The File
	 */

	public File getFile() {
		return file;
	}

}
