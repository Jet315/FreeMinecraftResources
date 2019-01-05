package me.jet315.elytraparkour.manager;

import org.bukkit.entity.Player;

public class ElytraPlayer {

    /**
     * Stores whether they are in map or not
     */
    private boolean isInMap = false;
    /**
     * Stores the current map they are in
     */
    private ElytraMap map;
    /**
     * Stores the ring number that they are currently on
     */
    private int ringNumber = -1;

    /**
     * Stores player object
     */
    private Player player;

    public ElytraPlayer(Player p){
        this.player = p;

    }



    public boolean isInMap() {
        return isInMap;
    }

    public void setInMap(boolean inMap) {
        isInMap = inMap;
    }


    public Player getPlayer() {
        return player;
    }

    public ElytraMap getMap() {
        return map;
    }

    public void setMap(ElytraMap map) {
        this.map = map;
    }

    /**
     * @return the ring number that they have flown through
     */
    public int getRingNumber() {
        return ringNumber;
    }
    public void setRingNumber(int ringNumber) {
         this.ringNumber = ringNumber;
    }
}
