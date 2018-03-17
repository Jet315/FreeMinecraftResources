package me.jet.cps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Jet on 28/06/2017.
 */
public class CPSPlayer {

    private CPS instance;
    public CPSPlayer(CPS instance){
        this.instance = instance;
    }


    /**
     * Contains the Player who is being checked and the amount of clicks they are clicking at
     */
    private HashMap<Player,Integer> playersClicks = new HashMap<>();

    /**
     * Contains the Player who is being checked and the player who is checking them
     */
    private HashMap<Player,Player> getWhoClicked = new HashMap<>();


    /**
     * Adds one to their current clicking score
     */
    public void addPlayerClick(Player p){
        if(playersClicks.containsKey(p)){
            if(playersClicks.get(p) == 0){
                playersClicks.put(p,1);
                startClickingClock(p);
            }else{
                playersClicks.put(p,playersClicks.get(p)+1);
            }
        }
    }


    /**
     * Determinds or not whether the player is currently being checked
     */
    public boolean isBeingChecked(Player p){
        if(playersClicks.containsKey(p)){
            return true;
        }
        return false;
    }



    /**
     * Activates a clicking check - As soon as the player clicks the clock will start
     * @Param playerBeingChecked The player who is being checked
     * @Param playerWhoIsChecking the player who is performing this test
     *
     */
    public void startClickingCheck(Player playerBeingChecked,Player playerWhoIsChecking){
        playersClicks.put(playerBeingChecked,0);
        if(playerBeingChecked == playerWhoIsChecking){
            return;
        }
        getWhoClicked.put(playerBeingChecked,playerWhoIsChecking);

    }
    /**
     * Starts the timer of one second
     */
    private void startClickingClock(Player p){
        if(getWhoClicked.containsKey(p)){

            if(!p.isOnline()){
                getWhoClicked.get(p).sendMessage(instance.getProperties().getPlayerLoggedOut().replaceAll("%PLAYER%",p.getName()));
                cancelClick(p);
                return;
            }
            if(!getWhoClicked.get(p).isOnline()){
                cancelClick(p);
                return;
            }
            getWhoClicked.get(p).sendMessage(instance.getProperties().getOtherCPSCheck().replaceAll("%PLAYER%",p.getName()));
        }else{
            p.sendMessage(instance.getProperties().getCpsCheckStart());
        }

        Bukkit.getScheduler ().runTaskLater (instance, () -> cancelClick(p), 20); //20 ticks equal 1 second


    }

    /**
     * Removes the correct players from the ArrayList
     */

    public void cancelClick(Player p){
        if(getWhoClicked.containsKey(p)){
            if(playersClicks.get(p) != 0 && getWhoClicked.get(p).isOnline()) {
                getWhoClicked.get(p).sendMessage(instance.getProperties().getOtherCPSCheck().replaceAll("%PLAYER%",p.getName()).replaceAll("%CPS%",String.valueOf(playersClicks.get(p))));
            }
            getWhoClicked.remove(p);
            playersClicks.remove(p);
            return;
        }
        if(playersClicks.containsKey(p)){
            if(playersClicks.get(p) != 0) {
                p.sendMessage(instance.getProperties().getCpsMessage().replaceAll("%CPS%",String.valueOf(playersClicks.get(p))));
            }

            playersClicks.remove(p);
        }
    }
}
