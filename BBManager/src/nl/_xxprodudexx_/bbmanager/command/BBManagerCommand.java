package nl._xxprodudexx_.bbmanager.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl._xxprodudexx_.bbmanager.event.BadBehaviourEvent;
import nl._xxprodudexx_.bbmanager.manage.PunishManager;
import nl._xxprodudexx_.bbmanager.offence.OffenceType;
import nl._xxprodudexx_.bbmanager.util.BBPlayer;
import nl._xxprodudexx_.bbmanager.util.PM;
import nl._xxprodudexx_.bbmanager.util.Tempban;

public class BBManagerCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			System.out.println("You must be a player to perform this command!");
			return true;
		}

		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("bb")) {
			if (p.hasPermission("bb.commands")) {
				if (args.length != 2) {
					new PM("&7Please use /bb <player> <offence>", p);
					return true;
				} else {
					Player t = Bukkit.getPlayer(args[0]);

					if (t == null) {
						new PM("&cThis player is not online!", p);
						return true;
					} else {
						ArrayList<OffenceType> offences = new ArrayList<OffenceType>();
						for (OffenceType type : OffenceType.values()) {
							offences.add(type);
						}

						for (OffenceType offenceArg : offences) {
							if (args[1].equalsIgnoreCase(offenceArg.getName())) {
								BBPlayer bbp = new BBPlayer(t.getUniqueId());
								bbp.updateProfile();
								PunishManager.getInstance().doPunishment(bbp, offenceArg);

								return true;
							}
						}
					}
				}
			} else {
				new PM("&cYou don't have permission to perform this command!", p);
				return true;
			}
		}

		return true;
	}

}
