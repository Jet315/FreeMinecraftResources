package me.jet315.elytraparkour.commands;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.manager.Ring;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateRing extends CommandExecutor{

    public CreateRing() {

        setCommand("elytraparkour");
        setPermission("elytraparkour.admin.createring");
        setLength(4);
        setPlayer();
        setUsage("/elytraparkour createring <map> <radius> <ringnumber>");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        String mapName = args[1];
        double radius;
        try{
            radius = Double.parseDouble(args[2]);
        }catch (NumberFormatException e){
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + args[2] + " is not a valid number!");
            return;
        }
        int ringID;
        try{
            ringID = Integer.parseInt(args[3]);
        }catch (NumberFormatException e){
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + args[3] + " is not a valid integer!");
            return;
        }
        Ring ring = new Ring(p.getLocation(),ringID,radius);
        Core.getInstance().getElytraManager().addRing(mapName,ring,p);
    }
}
