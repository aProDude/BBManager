package nl._xxprodudexx_.bbmanager.manage;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;
import nl._xxprodudexx_.bbmanager.util.Tempban;
import nl._xxprodudexx_.bbmanager.util.YamlFile;

public class DataManager {

	private DataManager() {
	}

	private static DataManager instance = new DataManager();

	public static DataManager getInstance() {
		return instance;
	}

	private Set<YamlFile> playerFiles = new HashSet<YamlFile>();
	private Set<Tempban> tempbans = new HashSet<Tempban>();
	private YamlFile playerFile;
	private YamlFile tempbanFile = new YamlFile("tempbans");
	private BBPlayer player;

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
				player = new BBPlayer(UUID.fromString(f.getName().replace(".yml", "")));
			}
		}
	}

	public void unloadFiles() {
		this.saveFiles();
		this.playerFiles.clear();
	}

	public void storeTempbans() {
		FileConfiguration c = this.tempbanFile.getConfig();
		for (Tempban ban : this.tempbans) {
			if (!ban.isEnded() && ban.isStarted()) {
				ConfigurationSection cs = c.createSection(ban.getUUID().toString());
				cs.set("Name", ban.getBBPlayer().getName());
				cs.set("Seconds", ban.getSecondsLeft());
			}
		}
		this.tempbanFile.save();
	}

	public void loadTempbans() {
		FileConfiguration c = this.tempbanFile.getConfig();
		for (String ban : c.getKeys(false)) {
			UUID uuid = UUID.fromString(ban);
			long seconds = c.getLong(ban + ".Seconds");
			Tempban tban = new Tempban(uuid, seconds);
			tban.startBan();
		}
	}

	public Tempban getTempban(UUID uuid) {
		for (Tempban tb : this.tempbans) {
			if (tb.getUUID().equals(uuid)) {
				return tb;
			}
		}
		return null;
	}

	public Tempban getTempban(Player target) {
		return getTempban(target.getUniqueId());
	}

	public Tempban getTempban(OfflinePlayer target) {
		return getTempban(target.getUniqueId());
	}

	public Tempban getTempban(BBPlayer target) {
		return getTempban(target.getUUID());
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

	public Set<Tempban> getTempbans() {
		return tempbans;
	}

	public YamlFile getTempbanFile() {
		return tempbanFile;
	}

	public boolean fileExists(UUID uuid) {
		return getPlayerFile(uuid) != null;
	}

	public boolean hasTempban(UUID uuid) {
		return getTempban(uuid) != null;
	}

}
