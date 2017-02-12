package nl._xxprodudexx_.bbmanager.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PM {
	
	/**
	 * By calling this contructor, you can send a message to the specified player
	 * This message converts color codes ( & ) to a color in the message
	 * The constructor can be called as following;
	 * 
	 * 	- PM pm = new PM("&aHey", <player>);
	 *  - new PM("&aHey", <player>);
	 * 
	 * @param message
	 * @param p
	 */
	public PM(String message, Player p){
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	/**
	 * This method calls the constructor of this class, for each player that is online.
	 * It basically sends a message to everybody, without everybody knowing the others received it to, 
	 * unless the message looks like a broadcast.
	 * @param message
	 */
	
	public static void all(String message){
		for (Player all : Bukkit.getOnlinePlayers()){
			new PM(message, all);
		}
	}

}
