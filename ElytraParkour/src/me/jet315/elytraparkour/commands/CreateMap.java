package me.jet315.elytraparkour.commands;

import me.jet315.elytraparkour.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CreateMap extends CommandExecutor {

    public CreateMap() {

        setCommand("elytraparkour");
        setPermission("elytraparkour.admin.createmap");
        setLength(2);
        setBoth();
        setUsage("/elytraparkour createmap <map>");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String mapName = args[1];
        if(Core.getInstance().getElytraManager().createArena(mapName)){
            sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "A map called "+ mapName + " has been created!");
            sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "Now set the spawn location using " +ChatColor.RED + "/ep setspawn " + mapName);
        }else{
            sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + "This map already exists!");
        }
    }
}
