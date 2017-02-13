package nl._xxprodudexx_.bbmanager.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import nl._xxprodudexx_.bbmanager.offence.OffenceType;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;

public class BBEvent extends Event {

	@Override
	public HandlerList getHandlers() {
		return new HandlerList();
	}

	private Player target;
	private OffenceType type;
	private BBPlayer bbplayer;

	public BBEvent(Player target, OffenceType type) {
		this.target = target;
		this.type = type;
		this.bbplayer = new BBPlayer(target.getUniqueId());
	}

	public Player getTarget() {
		return target;
	}

	public OffenceType getOffenceType() {
		return type;
	}

	public BBPlayer getBBPlayer() {
		return bbplayer;
	}

}
