package nl._xxprodudexx_.bbmanager.util;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.manage.DataManager;

public class Tempban {

	private HashMap<UUID, Long> tempbanMap = new HashMap<>();
	private boolean started;
	private int taskID;
	private UUID uuid;
	private BBPlayer bbplayer;
	private long duration_seconds, new_start_seconds;
	private boolean ended;

	public Tempban(UUID uuid, long seconds) {
		this.started = false;
		this.uuid = uuid;
		this.bbplayer = Main.getAPI().getBBPlayer(uuid);
		this.duration_seconds = seconds;
		this.tempbanMap.put(uuid, this.duration_seconds);
		this.ended = false;
	}

	public void startBan() {
		this.started = true;
		DataManager.getInstance().getTempbans().add(this);
		this.taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				tempbanMap.remove(uuid);
				ended = true;
			}
		}, 20 * this.tempbanMap.get(uuid));
	}

	public void stopBan() {
		this.started = false;
		long ended = this.tempbanMap.get(uuid);
		Bukkit.getScheduler().cancelTask(this.taskID);
		this.new_start_seconds = this.duration_seconds - ended;
		this.tempbanMap.remove(uuid);
		this.tempbanMap.put(uuid, this.new_start_seconds);
		DataManager.getInstance().getTempbans().remove(this);
	}

	public void deleteBan() {
		this.started = false;
		this.ended = true;
		DataManager.getInstance().getTempbans().remove(this);
		this.taskID = -1;
		this.uuid = null;
		this.duration_seconds = -1;
		this.new_start_seconds = -1;
	}
	
	public boolean isStarted(){
		return started;
	}

	public int taskID() {
		return taskID;
	}

	public UUID getUUID() {
		return uuid;
	}

	public BBPlayer getBBPlayer() {
		return this.bbplayer;
	}

	public long getStartDurationSeconds() {
		return duration_seconds;
	}

	public long getSecondsLeft() {
		return new_start_seconds;
	}

	public boolean isEnded() {
		return ended;
	}

}
