package me.jet315.elytraparkour.commands;

import me.jet315.elytraparkour.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn extends CommandExecutor{

    public SetSpawn() {

        setCommand("elytraparkour");
        setPermission("elytraparkour.admin.setspawn");
        setLength(2);
        setPlayer();
        setUsage("/elytraparkour setspawn <map>");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String mapName = args[1];
        Core.getInstance().getElytraManager().setSpawnLocation(mapName,((Player)sender));
        sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "Now set the first ring location using " + ChatColor.RED + "ep createring "+mapName +" <radius> 0");
    }
}
