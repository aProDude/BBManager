package nl._xxprodudexx_.bbmanager.manage;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.Main;
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

	public YamlFile createPlayerFile(UUID uuid) {
		while (getPlayerFile(uuid) == null) {
			playerFile = new YamlFile(uuid);
			this.playerFiles.add(playerFile);
		}
		return this.playerFile;
	}

	public void saveFiles() {
		for (YamlFile file : this.playerFiles) {
			file.save();
		}
	}

	public void loadFiles() {
		if (!Main.getInstance().getDataFolder().exists()) {
			Main.getInstance().getDataFolder().mkdirs();
		}

		File dataFolder = Main.getInstance().getDataFolder();
		File dir = new File(dataFolder, "Data");
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				this.playerFiles.add(new YamlFile(UUID.fromString(f.getName().replace(".yml", ""))));
			}
		}
	}

	public void unloadFiles() {
		this.saveFiles();
		this.playerFiles.clear();
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
