package nl._xxprodudexx_.bbmanager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.bukkit.plugin.java.JavaPlugin;

import nl._xxprodudexx_.bbmanager.api.BBManagerAPI;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;
import nl._xxprodudexx_.bbmanager.util.YamlFile;

public class Main extends JavaPlugin {

	private static Main instance;
	private static BBManagerAPI api = new BBManagerAPI();

	private HashSet<BBPlayer> bbPlayers = new HashSet<BBPlayer>();

	@Override
	public void onEnable() {
		// Set the instance of this class
		instance = this;

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

	public Collection<BBPlayer> getBBPlayers() {
		return Collections.checkedCollection(bbPlayers, BBPlayer.class);
	}
}
