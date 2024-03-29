package me.main__.MultiverseAdventureWorlds.commands;

import java.util.List;

import me.main__.MultiverseAdventureWorlds.MultiverseAdventureWorlds;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class EnableCommand extends BaseCommand {
	
	public EnableCommand(MultiverseAdventureWorlds plugin) {
        super(plugin);
        this.setName("Enable AdventureWorlds");
        this.setCommandUsage("/mvaw enable " + ChatColor.GREEN + "[WORLD]");
        this.setArgRange(0, 1);
        this.addKey("mvaw enable");
        this.addKey("mvawenable");
        this.setPermission("multiverse.adventure.enable", "Converts a world into an AdventureWorld.", PermissionDefault.OP);
    }

	@Override
	public void runCommand(CommandSender sender, List<String> args) {
		String world;
		if (args.isEmpty()) {
			if (sender instanceof Player) {
				world = ((Player) sender).getWorld().getName();
			}
			else {
				sender.sendMessage("If you want me to automatically recognize your world, you'd better be a player ;)");
				return;
			}
		}
		else {
			world = args.get(0);
		}
		
		//checks
		if (plugin.getMVAWInfo(world) != null) {
			sender.sendMessage("This world is already an AdventureWorld!");
			return;
		}
		
		if (plugin.getCore().getMVWorldManager().getMVWorld(world) == null) {
			sender.sendMessage("That world doesn't exist!");
			return;
		}
		
		
		plugin.createWorldWithNotifications(world, sender);
	}

}
