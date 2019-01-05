package me.jet315.elytraparkour.commands;

import me.jet315.elytraparkour.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Reload extends CommandExecutor {

    public Reload() {

        setCommand("elytraparkour");
        setPermission("elytraparkour.admin.reload");
        setLength(1);
        setBoth();
        setUsage("/elytraparkour reload");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        long startTime = System.currentTimeMillis();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Core.getInstance().getProperties().getPluginsPrefix() + "&aStarting Reload"));
        Core.getInstance().reloadPlugin();
        long endtime = System.currentTimeMillis() - startTime;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Core.getInstance().getProperties().getPluginsPrefix() + "&aReload Complete: &6" + endtime + "ms"));

    }
}
