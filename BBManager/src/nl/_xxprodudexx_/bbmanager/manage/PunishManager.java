package nl._xxprodudexx_.bbmanager.manage;

import java.util.Date;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.Main;
import nl._xxprodudexx_.bbmanager.offence.OffenceType;
import nl._xxprodudexx_.bbmanager.offence.OffenceType.Punishment;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;
import nl._xxprodudexx_.bbmanager.util.PM;
import nl._xxprodudexx_.bbmanager.util.Tempban;

public class PunishManager {

	private PunishManager() {
	}

	private static PunishManager instance = new PunishManager();

	public static PunishManager getInstance() {
		return instance;
	}

	private BBPlayer player;

	public void doPunishment(UUID uuid, OffenceType type) {
		Player target;
		this.player = (Main.getAPI().getBBPlayer(uuid) != null) ? Main.getAPI().getBBPlayer(uuid) : null;
		if (player.isOnline()) {
			target = player.getTarget();
		} else {
			target = null;
		}
		this.player.addBBPoints(type.getBBPoints());
		Punishment p = Punishment.NONE;
		int pts = player.getBBPoints();

		if (pts >= 0 && pts <= 25) {
			p = Punishment.WARNING;
			player.addBBWarning();
			new PM("&c&lWARNING: &r&7You have been warned!", target);
			new PM("&7Reason: &c" + type.getName(), target);
			new PM("&7You currently have &c" + player.getBBWarnings() + " &7BBWarnings.", target);
			new PM("&7You currently have &c" + pts + " &7BBPoints.", target);
			return;
		} else if (pts > 25 && pts <= 50) {
			p = Punishment.WARNING;
			player.addBBWarning();
			new PM("&c&lWARNING: &r&7You have been warned!", target);
			new PM("&7Reason: &c" + type.getName(), target);
			new PM("&7You currently have &c" + player.getBBWarnings() + " &7BBWarnings.", target);
			new PM("&7You currently have &c" + pts + " &7BBPoints.", target);
			new PM("&7Please watch your behaviour, you are closer to fierce punishment.", target);
			return;
		} else if (pts > 50 && pts <= 100) {
			p = Punishment.KICK;
			target.kickPlayer(ChatColor.translateAlternateColorCodes('&',
					"" + "&8&l[&c&lBBManager&8&l]" + "\n\n&cYou have been temp-banned." + "\n\n&cReason: &7"
							+ type.getName() + "\n&cPunishment: &7" + String.valueOf(p) + "\n&cBBPoints: &6" + pts
							+ "\n&cBBWarnings: &6" + player.getBBWarnings() + "\n\n&cPlease watch your behaviour!"));
			return;
		} else if (pts > 100 && pts <= 200) {
			p = Punishment.TEMPBAN;
			// if (Main.getAPI().getTempban(player) != null) {
			// Tempban ban = new Tempban(player.getUUID(), 60 * 60 * 24 * 3);
			// ban.startBan();
			// }
			Tempban ban = new Tempban(player.getUUID(), 1000 * 60 * 60);
			ban.execute();
			target.kickPlayer(ChatColor.translateAlternateColorCodes('&',
					"" + "&8&l[&c&lBBManager&8&l]" + "\n\n&cYou have been temp-banned." + "\n\n&cReason: &7"
							+ type.getName() + "\n&cPunishment: &7" + String.valueOf(p) + "\n&cBBPoints: &6" + pts
							+ "\n&cBBWarnings: &6" + player.getBBWarnings() + "\n\n&cPlease watch your behaviour!"));
			return;
		} else if (pts > 200) {
			p = Punishment.PERMBAN;
			target.kickPlayer(ChatColor.translateAlternateColorCodes('&',
					"" + "&8&l[&c&lBBManager&8&l]" + "\n\n&cYou have been banned." + "\n\n&cReason: &7" + type.getName()
							+ "\n&cPunishment: &7" + String.valueOf(p) + "\n&cBBPoints: &6" + pts + "\n&cBBWarnings: &6"
							+ player.getBBWarnings() + "\n\n&cPlease watch your behaviour!"));
			player.setBanned(true);
		}
	}

	public void doPunishment(Player target, OffenceType type) {
		this.doPunishment(target.getUniqueId(), type);
	}

	public void doPunishment(OfflinePlayer target, OffenceType type) {
		this.doPunishment(target.getUniqueId(), type);
	}

	public void doPunishment(BBPlayer target, OffenceType type) {
		this.doPunishment(target.getUUID(), type);
	}

}
