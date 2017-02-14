package nl._xxprodudexx_.bbmanager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import nl._xxprodudexx_.bbmanager.api.BBManagerAPI;
import nl._xxprodudexx_.bbmanager.command.BBManagerCommand;
import nl._xxprodudexx_.bbmanager.listener.BadBehaviourListener;
import nl._xxprodudexx_.bbmanager.manage.DataManager;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;
import nl._xxprodudexx_.bbmanager.util.YamlFile;

public class Main extends JavaPlugin {

	private static Main instance;
	private static BBManagerAPI api;

	private HashSet<BBPlayer> bbPlayers = new HashSet<BBPlayer>();

	@Override
	public void onEnable() {
		// Set the instance of this class
		instance = this;

		// Set instance of the API
		api = new BBManagerAPI();

		// Load all configuration-files
		// YamlFile.getPlayerDataFile().load();

		// Load all existing files
		DataManager.getInstance().loadFiles();

		// Load Tempbans
		DataManager.getInstance().loadTempbans();

		// Load all Listeners
		Bukkit.getPluginManager().registerEvents(new BadBehaviourListener(), this);

		// Load all Commads
		this.getCommand("bb").setExecutor(new BBManagerCommand());

	}

	@Override
	public void onDisable() {
		// Remove the instance of this class
		instance = null;

		// Remove the instance of the API
		api = null;

		DataManager.getInstance().storeTempbans();

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
