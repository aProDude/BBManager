package nl._xxprodudexx_.bbmanager;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	@Override
	public void onEnable(){
		instance = this;
	}
	
	@Override
	public void onDisable(){
		instance = null;
	}
	
	public static Main getInstance(){
		return instance;
	}
	

}
