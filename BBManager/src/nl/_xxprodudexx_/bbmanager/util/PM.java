package nl._xxprodudexx_.bbmanager.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PM {
	
	public PM(String message, Player p){
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void all(String message){
		for (Player all : Bukkit.getOnlinePlayers()){
			new PM(message, all);
		}
	}

}
