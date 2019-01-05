package me.jet315.elytraparkour.listeners;

import me.jet315.elytraparkour.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Core.getInstance().getElytraManager().getElytraPlayers().remove(e.getPlayer());
    }
}
