package nl._xxprodudexx_.bbmanager.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.manage.DataManager;

public class BBPlayer {

	private YamlFile file;
	private FileConfiguration c;
	private BBPlayer bbplayer;
	private UUID uuid;
	private String name;
	private int BBPoints;
	private int BBWarnings;
	private boolean isBanned;

	public BBPlayer(UUID uuid) {
		this.file = DataManager.getInstance().createPlayerFile(uuid);
		this.c = file.getConfig();
		this.bbplayer = this;
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
			this.file.save();
		} else {
			this.BBPoints = this.BBWarnings = 0;
			this.isBanned = false;
			this.file.save();
		}
		Main.getInstance().getBBPlayers().add(this);
		this.file.save();
	}

	public void updateProfile() {
		this.file = DataManager.getInstance().getPlayerFile(uuid);
		this.c = this.file.getConfig();
		this.bbplayer = this;
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
			this.file.save();
		} else {
			this.BBPoints = this.BBWarnings = 0;
			this.isBanned = false;
			this.file.save();
		}
		this.file.save();
	}

	public void destoryProfile() {
		if (c.getConfigurationSection("Data." + uuid.toString()) != null) {
			c.set("Data." + uuid.toString(), null);
		}

		this.file.save();
		Main.getInstance().getBBPlayers().remove(this);
		this.bbplayer = null;
	}

	public void addBBPoints(int points) {
		int newPoints = this.BBPoints + points;
		c.set("Data." + uuid.toString() + ".BBPoints", Integer.valueOf(newPoints));
		this.file.save();
		this.updateProfile();
	}

	public void setBBPoints(int points) {
		c.set("Data." + uuid.toString() + ".BBPoints", Integer.valueOf(points));
		this.file.save();
		this.updateProfile();
	}

	public void addBBWarning() {
		int newPoints = this.BBWarnings + 1;
		c.set("Data." + uuid.toString() + ".BBWarnings", Integer.valueOf(newPoints));
		this.file.save();
		this.updateProfile();
	}

	public void setBBWarnings(int warnings) {
		c.set("Data." + uuid.toString() + ".BBWarnings", Integer.valueOf(warnings));
		this.file.save();
		this.updateProfile();
	}

	public void setBanned(boolean banned) {
		c.set("Data." + uuid.toString() + ".Banned", Boolean.valueOf(banned));
		this.file.save();
		this.updateProfile();
	}

	public BBPlayer getBBPlayer() {
		return bbplayer;
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
