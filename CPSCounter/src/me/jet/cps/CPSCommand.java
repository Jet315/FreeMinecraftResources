package me.jet.cps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Jet on 28/06/2017.
 */
public class CPSCommand implements CommandExecutor {

    private CPSPlayer cpsPlayer;
    private CPS instance;

    public CPSCommand(CPS instance) {
        this.cpsPlayer = instance.getCpsPlayer();
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("cps")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 0) {
                    if(!(p.hasPermission("cps.check"))){
                        p.sendMessage(instance.getProperties().getNoPermission());
                        return true;
                    }
                    cpsPlayer.startClickingCheck(p,p);
                    p.sendMessage(instance.getProperties().getStartCPSCheck());
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("reload") && p.hasPermission("cps.reload")){
                        long startTime = System.currentTimeMillis();
                        instance.reloadProperties();
                        p.sendMessage(ChatColor.GREEN + "Config reloaded! This took " + String.valueOf(System.currentTimeMillis() - startTime) + "Ms");
                        return true;
                    }
                    Player playerToBeChecked = Bukkit.getPlayer(args[0]);
                    if(playerToBeChecked.isOnline()){
                        if(!p.getName().equalsIgnoreCase(playerToBeChecked.getDisplayName())){
                            if(sender.hasPermission("cps.checkother")) {
                                cpsPlayer.startClickingCheck(playerToBeChecked, p);
                                p.sendMessage(instance.getProperties().getOtherCPSCheckStart());
                            }else{
                                p.sendMessage(instance.getProperties().getOtherCPSCheckNoPermission());
                            }

                        }else{
                            cpsPlayer.startClickingCheck(p,p);
                            p.sendMessage(instance.getProperties().getStartCPSCheck());
                        }
                    }else{
                        p.sendMessage(instance.getProperties().getPlayerNotFound());
                    }
                    //Check if himself doing it


                }else{
                    p.sendMessage(instance.getProperties().getInvalidArgs());
                }
            }

        }

        return false;
    }
}
