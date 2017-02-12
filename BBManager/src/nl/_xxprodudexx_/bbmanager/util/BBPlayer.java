package nl._xxprodudexx_.bbmanager.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class BBPlayer {

	private FileConfiguration c = YamlFile.getPlayerDataFile().getConfig();

	private UUID uuid;
	private String name; 
	private int BBPoints;
	private int BBWarnings;
	private boolean isBanned;

	public BBPlayer(UUID uuid) {
		this.uuid = uuid;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getUniqueId().equals(uuid)) {
				this.name = Bukkit.getPlayer(uuid).getName();
			} else {
				this.name = Bukkit.getOfflinePlayer(uuid).getName();
			}
		}
		if (c.getString("Data." + uuid.toString()) != null) {
			this.BBPoints = c.getInt("Data." + uuid.toString() + ".BBPoints");
			this.BBWarnings = c.getInt("Data." + uuid.toString() + ".BBWarnings");
			this.isBanned = c.getBoolean("Data." + uuid.toString() + ".Banned");
		} else {
			this.BBPoints = this.BBWarnings = 0;
			this.isBanned = false;
		}
	}

	public void updateProfile() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getUniqueId().equals(uuid)) {
				this.name = Bukkit.getPlayer(uuid).getName();
			} else {
				this.name = Bukkit.getOfflinePlayer(uuid).getName();
			}
		}
		if (c.getString("Data." + uuid.toString()) != null) {
			this.BBPoints = c.getInt("Data." + uuid.toString() + ".BBPoints");
			this.BBWarnings = c.getInt("Data." + uuid.toString() + ".BBWarnings");
			this.isBanned = c.getBoolean("Data." + uuid.toString() + ".Banned");
		} else {
			this.BBPoints = this.BBWarnings = 0;
			this.isBanned = false;
		}
	}

	public void addBBPoints(int points) {
		int newPoints = this.BBPoints + points;
		c.set("Data." + uuid.toString() + ".BBPoints", Integer.valueOf(newPoints));
		YamlFile.getPlayerDataFile().save();
		this.updateProfile();
	}

	public void setBBPoints(int points) {
		c.set("Data." + uuid.toString() + ".BBPoints", Integer.valueOf(points));
		YamlFile.getPlayerDataFile().save();
		this.updateProfile();
	}

	public void addBBWarning() {
		int newPoints = this.BBPoints + 1;
		c.set("Data." + uuid.toString() + ".BBPoints", Integer.valueOf(newPoints));
		YamlFile.getPlayerDataFile().save();
		this.updateProfile();
	}

	public void setBBWarnings(int warnings) {
		c.set("Data." + uuid.toString() + ".BBPoints", Integer.valueOf(warnings));
		YamlFile.getPlayerDataFile().save();
		this.updateProfile();
	}

	public void setBanned(boolean banned) {
		c.set("Data." + uuid.toString() + ".Banned", Boolean.valueOf(banned));
		YamlFile.getPlayerDataFile().save();
		this.updateProfile();
	}

	public UUID getUUID() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public int getBBPoints() {
		return BBPoints;
	}

	public int getBBWarnings() {
		return BBWarnings;
	}

	public boolean isBanned() {
		return isBanned;
	}

}
