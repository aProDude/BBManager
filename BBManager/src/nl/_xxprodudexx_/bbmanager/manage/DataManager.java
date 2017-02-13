package nl._xxprodudexx_.bbmanager.manage;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.util.YamlFile;

public class DataManager {

	private DataManager() {
	}

	private static DataManager instance = new DataManager();

	public static DataManager getInstance() {
		return instance;
	}

	private Set<YamlFile> playerFiles = new HashSet<YamlFile>();
	private YamlFile playerFile;

	public void createPlayerFile(UUID uuid) {
		while (getPlayerFile(uuid) == null) {
			playerFile = new YamlFile(uuid);
			this.playerFiles.add(playerFile);
		}
		
	}

	public YamlFile getPlayerFile(UUID uuid) {
		for (YamlFile file : this.playerFiles) {
			if (file.getName().equals(uuid.toString())) {
				return file;
			}
		}
		return null;
	}

	public YamlFile getPlayerFile(Player p) {
		return getPlayerFile(p.getUniqueId());
	}

	public YamlFile getPlayerFile(OfflinePlayer p) {
		return getPlayerFile(p.getUniqueId());
	}

	public Set<YamlFile> getPlayerFiles() {
		return playerFiles;
	}

}
