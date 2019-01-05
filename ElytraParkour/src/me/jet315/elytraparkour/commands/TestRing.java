package me.jet315.elytraparkour.commands;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.utils.ParticleUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class TestRing extends CommandExecutor{

    public TestRing() {

        setCommand("elytraparkour");
        setPermission("elytraparkour.admin.testring");
        setLength(2);
        setPlayer();
        setUsage("/elytraparkour testring <radius>");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "Particles spawning!");
        double radius;
        try{
            radius = Double.parseDouble(args[1]);
        }catch (NumberFormatException e){
            sender.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() +ChatColor.RED + args[1] + " is not a number!");
            return;
        }
        Player p = (Player) sender;
        ArrayList<Location> locations = ParticleUtils.generateCircleLocations((p).getLocation(),radius,Core.getInstance().getProperties().getNumberOfParticlesToSpawnPerRing());

        new BukkitRunnable() {
            int i = 4;

            public void run() {
                if(i == 4){
                    for(Location loc : locations){
                        p.spawnParticle(Core.getInstance().getProperties().getTestingRingParticle(),loc,1,0,0,0,0,null);
                    }
                }
                if(i == 3){
                    for(Location loc : locations){
                        p.spawnParticle(Core.getInstance().getProperties().getTestingRingParticle(),loc,1,0,0,0,0,null);
                    }
                }
                if(i == 2){
                    for(Location loc : locations){
                        p.spawnParticle(Core.getInstance().getProperties().getTestingRingParticle(),loc,1,0,0,0,0,null);
                    }
                }
                if(i == 1){
                    for(Location loc : locations){
                        p.spawnParticle(Core.getInstance().getProperties().getTestingRingParticle(),loc,1,0,0,0,0,null);
                    }
                }
                if (i == 0) {
                    for(Location loc : locations){
                        p.spawnParticle(Core.getInstance().getProperties().getTestingRingParticle(),loc,1,0,0,0,0,null);
                    }
                    cancel();
                }
                i--;
            }

        }.runTaskTimer(Core.getInstance(), 0L, 10L);
    }
}
