package nl._xxprodudexx_.bbmanager.api;

import java.util.UUID;

import nl._xxprodudexx_.bbmanager.util.BBPlayer;

public class BBManagerAPI {

	public void addBBPoints(UUID uuid, int points) {
		BBPlayer p = new BBPlayer(uuid);
		p.addBBPoints(points);
		p.updateProfile();
	}

	public void addBBWarning(UUID uuid) {
		BBPlayer p = new BBPlayer(uuid);
		p.addBBWarning();
		p.updateProfile();
	}

	public void setBBPoints(UUID uuid, int points) {
		BBPlayer p = new BBPlayer(uuid); 
		p.setBBPoints(points);
		p.updateProfile();
	}

	public void setBBWarnings(UUID uuid, int warnings) {
		BBPlayer p = new BBPlayer(uuid);
		p.setBBWarnings(warnings);
		p.updateProfile();
	}
	
	public void setBanned(UUID uuid, boolean banned){
		BBPlayer p = new BBPlayer(uuid);
		p.setBanned(banned);
		p.updateProfile();
	}

	public int getBBPoints(UUID uuid) {
		return new BBPlayer(uuid).getBBPoints();
	}

	public int getBBWarnings(UUID uuid) {
		return new BBPlayer(uuid).getBBWarnings();
	}

	public boolean isBanned(UUID uuid) {
		return new BBPlayer(uuid).isBanned();
	}

	public BBPlayer getBBPlayer(UUID uuid) {
		return new BBPlayer(uuid);
	}

}
