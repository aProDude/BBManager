package nl._xxprodudexx_.bbmanager.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.offence.OffenceType;
import nl._xxprodudexx_.bbmanager.offence.OffenceType.Punishment;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;

public class BadBehaviourEvent extends Event implements Cancellable {

	private boolean isCancelled;
	private Player target;
	private OffenceType type;
	private Punishment punishment;
	private BBPlayer bbplayer;

	public BadBehaviourEvent(boolean isAsync, Player target, OffenceType type, Punishment punishment) {
		super(isAsync);
		this.isCancelled = false;
		this.target = target;
		this.type = type;
		this.punishment = punishment;
		if (Main.getAPI().getBBPlayer(target) == null) {
			this.bbplayer = new BBPlayer(target.getUniqueId());
		} else {
			this.bbplayer = Main.getAPI().getBBPlayer(target);
		}
		this.bbplayer.addBBPoints(this.type.getBBPoints());
		if (punishment == Punishment.WARNING) {
			this.bbplayer.addBBWarning();
		}
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

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.isCancelled = cancelled;
	}

}
