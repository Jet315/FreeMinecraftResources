package me.jet315.elytraparkour.listeners;

import me.jet315.elytraparkour.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

public class GlideToggleEvent implements Listener {

    @EventHandler
    public void onElytraToggle(EntityToggleGlideEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            //check if they disabled elytra flight
            if(!e.isGliding()){
                if(Core.getInstance().getElytraManager().getElytraPlayers().containsKey(p) && Core.getInstance().getElytraManager().getElytraPlayers().get(p).isInMap()){
                    if(Core.getInstance().getProperties().isTeleportToMapSpawnIfStopsGliding()){
                        p.teleport(Core.getInstance().getElytraManager().getElytraPlayers().get(p).getMap().getSpawnLocation());
                        Core.getInstance().getElytraManager().getElytraPlayers().get(p).setInMap(false);

                    }
                    if(!Core.getInstance().getProperties().getStopGlidingMessage().equalsIgnoreCase("none")){
                        p.sendMessage(Core.getInstance().getProperties().getStopGlidingMessage().replaceAll("%PREFIX%",Core.getInstance().getProperties().getPluginsPrefix()));
                    }
                }

            }
        }
    }
}
