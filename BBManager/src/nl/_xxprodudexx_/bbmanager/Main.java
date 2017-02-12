package nl._xxprodudexx_.bbmanager;

import org.bukkit.plugin.java.JavaPlugin;

import nl._xxprodudexx_.bbmanager.api.BBManagerAPI;
import nl._xxprodudexx_.bbmanager.util.YamlFile;

public class Main extends JavaPlugin implements BBManagerAPI {

	private static Main instance;
	private static BBManagerAPI api;

	@Override
	public void onEnable() {
		// Set the instance of this class
		instance = this;

		// Set the instance of the API to this class
		api = this;

		// Load all configuration-files
		YamlFile.getPlayerDataFile().load();
	}

	@Override
	public void onDisable() {
		// Remove the instance of this class
		instance = null;
	}

	// Return the instance of this class
	public static Main getInstance() {
		return instance;
	}

	public static BBManagerAPI getAPI() {
		return api;
	}
}
