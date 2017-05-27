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
import nl._xxprodudexx_.bbmanager.util.Tempban;

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
		DataManager.getInstance().getTempbanFile().load();

		// Load Tempbans
		DataManager.getInstance().loadTempbans();
		for (Tempban ban : DataManager.getInstance().getTempbans()) {
			System.out.println(ban.getEnd());
		}
		
		//TODO FIX UNBAN THREAD

		// Load all Listeners
		Bukkit.getPluginManager().registerEvents(new BadBehaviourListener(), this);

		// Load all Commads
		this.getCommand("bb").setExecutor(new BBManagerCommand());

	}

	@Override
	public void onDisable() {
		// Store data of existing tempbans
		DataManager.getInstance().storeTempbans();

		// Remove the instance of the API
		api = null;

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
