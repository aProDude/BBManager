package nl._xxprodudexx_.bbmanager.api;

import java.util.UUID;

import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;

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
		for (BBPlayer bbp : Main.getInstance().getBBPlayers()) {
			if (bbp.getUUID().equals(p.getUniqueId())) {
				return bbp;
			}
		}
		return null;
	}

}
