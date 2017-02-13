package nl._xxprodudexx_.bbmanager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import nl._xxprodudexx_.bbmanager.api.BBManagerAPI;
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
		for (YamlFile file : DataManager.getInstance().getPlayerFiles()) {
			System.out.println(file.getName());
		}

		// Load all Listeners
		Bukkit.getPluginManager().registerEvents(new BadBehaviourListener(), this);

		// Create Test Module
		BBPlayer test = new BBPlayer(UUID.randomUUID());
		test.addBBPoints(50);
		test.addBBWarning();
		test.setBanned(false);
		System.out.println(test.getName());
		System.out.println(test.getBBPoints());
		System.out.println(test.getBBWarnings());
	}

	@Override
	public void onDisable() {
		// Remove the instance of this class
		instance = null;
		
		// Remove the instance of the API
		api = null;
		
	
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
