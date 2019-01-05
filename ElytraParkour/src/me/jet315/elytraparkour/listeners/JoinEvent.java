package me.jet315.elytraparkour.listeners;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.manager.ElytraPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Core.getInstance().getElytraManager().getElytraPlayers().put(e.getPlayer(),new ElytraPlayer(e.getPlayer()));
    }
}
