package me.jet315.elytraparkour.commands;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.manager.Ring;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteMap extends CommandExecutor{

    public DeleteMap() {

        setCommand("elytraparkour");
        setPermission("elytraparkour.admin.deletemap");
        setLength(2);
        setPlayer();
        setUsage("/elytraparkour delete <map> ");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        String mapName = args[1];

        if(Core.getInstance().getElytraManager().deleteMap(mapName)){
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() +ChatColor.GREEN + "the map "+ ChatColor.GREEN + mapName + " has been deleted!");
        }else{
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() +ChatColor.RED + "the map "+ ChatColor.GREEN + mapName +  ChatColor.RED +" does not exist!");
        }
    }
}
