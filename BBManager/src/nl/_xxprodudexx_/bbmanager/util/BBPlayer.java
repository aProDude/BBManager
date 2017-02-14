package nl._xxprodudexx_.bbmanager.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.manage.DataManager;

public class BBPlayer {

	private YamlFile file;
	private FileConfiguration c;
	private BBPlayer bbplayer;
	private boolean isOnline;
	private Player target;
	private OfflinePlayer offlineTarget;
	private UUID uuid;
	private String name;
	private int BBPoints;
	private int BBWarnings;
	private boolean isBanned;

	public BBPlayer(UUID uuid) {
		if (!DataManager.getInstance().fileExists(uuid)) {
			this.file = DataManager.getInstance().createPlayerFile(uuid);
		} else {
			this.file = DataManager.getInstance().getPlayerFile(uuid);
		}
		this.c = file.getConfig();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getUniqueId().equals(uuid)) {
				this.name = Bukkit.getPlayer(uuid).getName();
				this.target = Bukkit.getPlayer(uuid);
				this.isOnline = true;
			} else {
				this.name = Bukkit.getOfflinePlayer(uuid).getName();
				this.offlineTarget = Bukkit.getOfflinePlayer(uuid);
				this.isOnline = false;
			}
		}
		if (name != null) {
			c.set("Data.Name", name);
		}
		if (uuid != null) {
			c.set("Data.UUID", uuid.toString());
		}
		if (c.get("Data.BBPoints") == null) {
			c.set("Data.BBPoints", Integer.valueOf(0));
		}
		if (c.get("Data.BBWarnings") == null) {
			c.set("Data.BBWarnings", Integer.valueOf(0));
		}
		if (c.get("Data.Banned") == null) {
			c.set("Data.Banned", Boolean.valueOf(false));
		}
		this.bbplayer = this;
		this.uuid = uuid;
		this.name = c.getString("Data.Name");
		this.BBPoints = c.getInt("Data.BBPoints");
		this.BBWarnings = c.getInt("Data.BBWarnings");
		this.isBanned = c.getBoolean("Data.Banned");

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
				this.target = Bukkit.getPlayer(uuid);
				this.isOnline = true;
			} else {
				this.name = Bukkit.getOfflinePlayer(uuid).getName();
				this.offlineTarget = Bukkit.getOfflinePlayer(uuid);
				this.isOnline = false;
			}
		}
		if (name != null) {
			c.set("Data.Name", name);
		}
		if (uuid != null) {
			c.set("Data.UUID", uuid.toString());
		}
		if (c.get("Data.BBPoints") == null) {
			c.set("Data.BBPoints", Integer.valueOf(0));
		}
		if (c.get("Data.BBWarnings") == null) {
			c.set("Data.BBWarnings", Integer.valueOf(0));
		}
		if (c.get("Data.Banned") == null) {
			c.set("Data.Banned", Boolean.valueOf(false));
		}
		this.bbplayer = this;
		this.name = c.getString("Data.Name");
		this.BBPoints = c.getInt("Data.BBPoints");
		this.BBWarnings = c.getInt("Data.BBWarnings");
		this.isBanned = c.getBoolean("Data.Banned");

		Main.getInstance().getBBPlayers().add(this);
		this.file.save();
	}

	public void destoryProfile() {
		if (c.getConfigurationSection("Data") != null) {
			c.set("Data", null);
		}

		this.file.save();
		Main.getInstance().getBBPlayers().remove(this);
		this.bbplayer = null;
	}

	public void addBBPoints(int points) {
		int newPoints = this.BBPoints + points;
		c.set("Data.BBPoints", Integer.valueOf(newPoints));
		this.file.save();
		this.updateProfile();
	}

	public void setBBPoints(int points) {
		c.set("Data.BBPoints", Integer.valueOf(points));
		this.file.save();
		this.updateProfile();
	}

	public void addBBWarning() {
		int newPoints = this.BBWarnings + 1;
		c.set("Data.BBWarnings", Integer.valueOf(newPoints));
		this.file.save();
		this.updateProfile();
	}

	public void setBBWarnings(int warnings) {
		c.set("Data.BBWarnings", Integer.valueOf(warnings));
		this.file.save();
		this.updateProfile();
	}

	public void setBanned(boolean banned) {
		c.set("Data.Banned", Boolean.valueOf(banned));
		this.file.save();
		this.updateProfile();
	}

	public BBPlayer getBBPlayer() {
		return bbplayer;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public Player getTarget() {
		return target;
	}

	public OfflinePlayer getOfflineTarget() {
		return offlineTarget;
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
