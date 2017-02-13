package nl._xxprodudexx_.bbmanager.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.offence.OffenceType;
import nl._xxprodudexx_.bbmanager.offence.OffenceType.Punishment;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;

public class BadBehaviourEvent extends Event {

	private Player target;
	private OffenceType type;
	private Punishment punishment;
	private BBPlayer bbplayer;

	public BadBehaviourEvent(boolean isAsync, Player target, OffenceType type, Punishment punishment) {
		super(isAsync);
		this.target = target;
		this.type = type;
		this.punishment = punishment;
		this.bbplayer = Main.getAPI().getBBPlayer(target);
	}

	public Player getTarget() {
		return target;
	}

	public OffenceType getOffenceType() {
		return type;
	}

	public Punishment getPunishment() {
		return punishment;
	}

	public BBPlayer getBBPlayer() {
		return bbplayer;
	}

	@Override
	public HandlerList getHandlers() {
		return new HandlerList();
	}

	public static HandlerList getHandlerList() {
		return new HandlerList();
	}

}
