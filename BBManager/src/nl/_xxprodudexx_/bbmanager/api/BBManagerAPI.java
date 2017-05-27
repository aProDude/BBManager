package nl._xxprodudexx_.bbmanager.api;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.manage.DataManager;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;
import nl._xxprodudexx_.bbmanager.util.Tempban;

public class BBManagerAPI {

	public void addBBPoints(UUID uuid, int points) {
		if (this.getBBPlayer(uuid) != null) {
			BBPlayer p = this.getBBPlayer(uuid);
			p.addBBPoints(points);
			p.updateProfile();
		}
	}

	public void addBBWarning(UUID uuid) {
		if (this.getBBPlayer(uuid) != null) {
			BBPlayer p = this.getBBPlayer(uuid);
			p.addBBWarning();
			p.updateProfile();
		}
	}

	public void setBBPoints(UUID uuid, int points) {
		if (this.getBBPlayer(uuid) != null) {
			BBPlayer p = this.getBBPlayer(uuid);
			p.setBBPoints(points);
			p.updateProfile();
		}
	}

	public void setBBWarnings(UUID uuid, int warnings) {
		if (this.getBBPlayer(uuid) != null) {
			BBPlayer p = this.getBBPlayer(uuid);
			p.setBBWarnings(warnings);
			p.updateProfile();
		}
	}

	public void setBanned(UUID uuid, boolean banned) {
		if (this.getBBPlayer(uuid) != null) {
			BBPlayer p = this.getBBPlayer(uuid);
			p.setBanned(banned);
			p.updateProfile();
		}
	}

	// public void tempban(UUID uuid, long seconds) {
	// if (getTempban(uuid) == null) {
	// new Tempban(uuid, seconds);
	// }
	// }
	//
	// public void startTempban(UUID uuid) {
	// if (getTempban(uuid) != null) {
	// getTempban(uuid).startBan();
	// }
	// }
	//
	// public Tempban getTempban(UUID uuid) {
	// for (Tempban tb : DataManager.getInstance().getTempbans()) {
	// if (tb.getUUID().equals(uuid)) {
	// return tb;
	// }
	// }
	// return null;
	// }
	//
	// public Tempban getTempban(Player target) {
	// return getTempban(target.getUniqueId());
	// }
	//
	// public Tempban getTempban(OfflinePlayer target) {
	// return getTempban(target.getUniqueId());
	// }
	//
	// public Tempban getTempban(BBPlayer target) {
	// return getTempban(target.getUUID());
	// }
	//
	// public boolean hasTempban(UUID uuid) {
	// return getTempban(uuid) != null;
	// }

	public int getBBPoints(UUID uuid) {
		if (this.getBBPlayer(uuid) != null) {
			return this.getBBPlayer(uuid).getBBPoints();
		} else {
			return 0;
		}
	}

	public int getBBWarnings(UUID uuid) {
		if (this.getBBPlayer(uuid) != null) {
			return this.getBBPlayer(uuid).getBBWarnings();
		} else {
			return 0;
		}
	}

	public boolean isBanned(UUID uuid) {
		if (this.getBBPlayer(uuid) != null) {
			return this.getBBPlayer(uuid).isBanned();
		} else {
			return false;
		}
	}

	public BBPlayer getBBPlayer(UUID uuid) {
		for (BBPlayer bbp : Main.getInstance().getBBPlayers()) {
			if (bbp.getUUID().equals(uuid)) {
				return bbp;
			}
		}
		return null;
	}

	public BBPlayer getBBPlayer(Player p) {
		return this.getBBPlayer(p.getUniqueId());
	}

}
