package me.jet315.elytraparkour.commands;

import me.jet315.elytraparkour.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler implements org.bukkit.command.CommandExecutor {

    private Map<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

    public CommandHandler() {
        //Player commands
        commands.put("createmap", new CreateMap());
        commands.put("createring", new CreateRing());
        commands.put("setspawn", new SetSpawn());
        commands.put("delete", new DeleteMap());
        commands.put("reload", new Reload());
        commands.put("testring", new TestRing());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("elytraparkour")) {

            if (args.length == 0) {
                if(sender.hasPermission("elytraparkour.help")){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&m----------"+Core.getInstance().getProperties().getPluginsPrefix().replaceAll(" ","")+"&a&m----------"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a/EP createmap <map> &d- &bCreates a map with the specified name"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a/EP setspawn <map> &d- &bSets a spawnpoint with the specified name"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a/EP testring &d- &bCan be used to view where a ring will spawn"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a/EP createring <map> <radius> <number>&d- &bCreates a ring at the location, with the specified number (rings start from 0)"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a/EP delete <map> &d- &bDeletes a map"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a/EP reload &d- &bReloads the configuration files"));

                }else{
                    sender.sendMessage(Core.getInstance().getProperties().getNoPermissions().replaceAll("%PREFIX%",Core.getInstance().getProperties().getPluginsPrefix()));
                }

                return true;
            }

            if (args[0] != null) {
                String name = args[0].toLowerCase();
                if (commands.containsKey(name)) {
                    final CommandExecutor command = commands.get(name);

                    if (command.getPermission() != null && !sender.hasPermission(command.getPermission())) {
                        sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + "You do not have permission to use this command!");
                        return true;
                    }

                    if (!command.isBoth()) {
                        if (command.isConsole() && sender instanceof Player) {
                            sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + "Only console can use that command!");
                            return true;
                        }
                        if (command.isPlayer() && sender instanceof ConsoleCommandSender) {
                            sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + "Only players can use that command!");
                            return true;
                        }
                    }

                    if (command.getLength() > args.length) {
                        sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + "Usage: " + ChatColor.AQUA + command.getUsage());
                        return true;
                    }

                    command.execute(sender, args);
                }else{
                    if(sender.hasPermission("elytraparkour.help")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&m----------" + Core.getInstance().getProperties().getPluginsPrefix().replaceAll(" ", "") + "&a&m----------"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/EP createmap <map> &d- &bCreates a map with the specified name"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/EP setspawn <map> &d- &bSets a spawnpoint with the specified name"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/EP testring &d- &bCan be used to view where a ring will spawn"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/EP createring <map> <radius> <number>&d- &bCreates a ring at the location, with the specified number (rings start from 0)"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/EP delete <map> &d- &bDeletes a map"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/EP reload &d- &bReloads the configuration files"));

                    }
                    }
            }
        }

        return false;
    }
}