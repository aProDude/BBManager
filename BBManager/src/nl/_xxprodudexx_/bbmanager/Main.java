package nl._xxprodudexx_.bbmanager;

import org.bukkit.plugin.java.JavaPlugin;

import nl._xxprodudexx_.bbmanager.util.YamlFile;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	@Override
	public void onEnable(){
		//Set the instance of this class
		instance = this;
		
		//Load all configuration-files
		YamlFile.getPlayerDataFile().load();
	}
	
	@Override
	public void onDisable(){
		//Remove the instance of this class
		instance = null;
	}
	
	//Return the instance of this class
	public static Main getInstance(){
		return instance;
	}
	

}
