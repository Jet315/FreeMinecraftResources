package me.jet315.elytraparkour;

import me.jet315.elytraparkour.manager.ElytraPlayer;
import org.bukkit.entity.Player;

public class ElytraParkourAPI {

    /**
     *
     * @param p The player
     * @return An ElytraPlayer object for the requested player, null if player does not exist
     */
    public static ElytraPlayer getElytraPlayer(Player p){
        if(p != null && p.isOnline()){
            if(Core.getInstance().getElytraManager().getElytraPlayers().containsKey(p)){
                return Core.getInstance().getElytraManager().getElytraPlayers().get(p);
            }
        }
        return null;
    }





}
