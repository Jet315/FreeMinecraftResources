package me.jet315.itemeffects.commands;

import me.jet315.itemeffects.Core;
import me.jet315.itemeffects.utils.ItemRenaming;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jet on 14/02/2018.
 */
public class CommandHandler implements CommandExecutor {

    private Core instance;

    public CommandHandler(Core instance){
        this.instance = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("itemeffect")) {

            if(sender.hasPermission("itemeffect.give") || sender.hasPermission("itemeffect.reload")){
                if(args.length == 1 && args[0].equals("reload")){
                    instance.reloadProperties();
                    sender.sendMessage(ChatColor.GREEN + "Configuration file reloaded!");
                    return true;
                }
                //Check the length of arguments is == 4 && first is equalTo give
                if(args.length == 4 && args[0].equalsIgnoreCase("give")){
                    Player p = Bukkit.getPlayer(args[1]);
                    //check the player is online
                    if(p != null){
                        PotionEffectType effect = PotionEffectType.getByName(args[2]);
                        //Check effect is not null
                        if(effect != null){
                            //Try to parse the integer given
                            int level;
                            try{
                                level = Integer.parseInt(args[3]);
                                if(level > 3){
                                    sender.sendMessage(ChatColor.RED + "3 is the maximum level allowed");
                                }

                            }catch (NumberFormatException e){
                                sender.sendMessage(ChatColor.RED + " " + args[3] + "is not an integer!");
                                return true;
                            }
                            //Convert number to roman numeral
                            String romanNumeral = "I";
                            if(level == 2){
                                romanNumeral = "II";
                            }else if(level == 3){
                                romanNumeral = "III";
                            }
                            String itemName = ItemRenaming.getLikeName(args[2]);
                            //Create the item, and give it to the player
                            ItemStack item = instance.getProperties().getEffectItem();
                            ItemMeta itemMeta = item.getItemMeta();

                            //Capitalise & apply chat formating
                            String displayName = ChatColor.RED + itemName.substring(0, 1).toUpperCase() + itemName.substring(1).toLowerCase() + " " + romanNumeral;

                            itemMeta.setDisplayName(displayName);

                            item.setItemMeta(itemMeta);
                            //Give to player
                            p.getInventory().addItem(item);

                            //Send sender message
                            sender.sendMessage(ChatColor.GREEN + "Success!");




                        }else{
                            sender.sendMessage(ChatColor.RED + "Try: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html\n" + args[2]  +" is an invalid potion effect");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "That player is not online!");
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "Wrong arguments: /itemeffect give <player> <effecttype> <level>");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "No Permission");
            }
        }
        return true;
    }
}
