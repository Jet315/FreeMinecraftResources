package me.jet.cps;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jet on 28/06/2017.
 */
public class CPS extends JavaPlugin implements Listener {

    private CPSPlayer cpsPlayer;
    private ActionBar actionBar;
    private Properties properties;

    public void onEnable() {

        if(!getServer().getPluginManager().isPluginEnabled("ActionBarAPI")){
            System.out.println("CPS > ActionBarAPI is required");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        properties = new Properties(this);
        cpsPlayer = new CPSPlayer(this);
        getCommand("cps").setExecutor(new CPSCommand(this));
        Bukkit.getPluginManager().registerEvents(this, this);
        this.actionBar = new ActionBar(this);
        actionBar.startClock();
    }

    public void onDisable() {
    }


    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (cpsPlayer.isBeingChecked(p)) {
            if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
                cpsPlayer.addPlayerClick(p);
            }
        }

        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
            actionBar.addCPS(p);
        }
    }

    public void reloadProperties() {
        reloadConfig();
        properties = null;
        properties = new Properties(this);
    }

    public Properties getProperties() {
        return properties;
    }

    public CPSPlayer getCpsPlayer() {
        return cpsPlayer;
    }
}
