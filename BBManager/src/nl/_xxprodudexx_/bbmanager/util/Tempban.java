package nl._xxprodudexx_.bbmanager.util;

import java.util.UUID;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.manage.DataManager;

public class Tempban {

	private UUID uuid;
	private BBPlayer bbplayer;
	private long millis;
	private long end;

	public Tempban(UUID uuid, long millis) {
		this.uuid = uuid;
		this.bbplayer = Main.getAPI().getBBPlayer(uuid);
		this.millis = millis;
		this.end = System.currentTimeMillis() + this.millis;

	}

	public void execute() {
		long current = System.currentTimeMillis();
		long end = current + this.millis;
		// calculate and store
		DataManager.getInstance().getTempbans().add(this);
	}

	public UUID getUUID() {
		return uuid;
	}

	public BBPlayer getBBPlayer() {
		return bbplayer;
	}

	public long getMillis() {
		return millis;
	}

	public long getEnd() {
		return end;
	}

}
