package me.jet.cps;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jet on 02/02/2018.
 */
public class ActionBar {

    private CPS instance;

    //Stores players being checked / current clicks
    private HashMap<Player,Integer> players = new HashMap<>();

    public ActionBar(CPS instance){
        this.instance = instance;
    }


    public void startClock(){
        //Every 1 second
        Bukkit.getScheduler().runTaskTimer(instance, new Runnable() {
            @Override
            public void run() {

                    //Display current stats
                    for(Player p : players.keySet()) {
                        if (p.hasPermission("cps.actionbar")) {
                            int clicks = players.get(p);
                            if(clicks == 0 && instance.getProperties().hideActionBar){
                                continue;
                            }
                            ActionBarAPI.sendActionBar(p, instance.getProperties().getActionBarText().replaceAll("%CPS%", String.valueOf(players.get(p))));
                        }
                    }
                    addPlayersToList();


            }
        },0,20L);
    }

    public void addPlayersToList(){
        //removes current unique ids
        players.clear();
        for(Player p : Bukkit.getOnlinePlayers()){
            players.put(p,0);
        }

    }


    /**
     *
     * @param p Adds one click to this player
     */
    public void addCPS(Player p){
        if(players.containsKey(p)){
            players.put(p,players.get(p)+1);
        }
    }


}
