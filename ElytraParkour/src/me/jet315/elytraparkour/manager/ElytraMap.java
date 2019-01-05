package me.jet315.elytraparkour.manager;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.utils.RingType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ElytraMap {

    /**
     * Stores the rings around the map, i
     */
    private HashMap<Integer,Ring> mapRings = new HashMap<>();

    /**
     * Stores starting/ending ring points
     */
    private Ring startingRing;
    private Ring endingRing;

    /**
     * Stores the map name
     */
    private String id;

    /**
     * Stores spawn location of the map
     */
    private Location spawnLocation;

    public ElytraMap(String mapName){
        this.id = mapName;
    }


    /**
     * Adds a ring to a course
     * @param ring a Ring object
     */
    public void addRingToMap(Player p, Ring ring){
        int ringId = ring.getNumberID();
        //Check if starting ring
        if(ringId == 0){
            ring.setRingType(RingType.FIRST);
            ring.setParticleType(Core.getInstance().getProperties().getFirstRingParticles());

            startingRing = ring;
            if(mapRings.containsKey(0)){
                mapRings.remove(0);
                mapRings.put(0,ring);
                p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "The map " + ChatColor.AQUA + id + ChatColor.GREEN +" has had its first ring "+  ChatColor.RED + "replaced!");
                return;
            }
            mapRings.put(0,ring);
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "The map " + ChatColor.AQUA + id + ChatColor.GREEN +" has had its first ring added!");
            return;
        }

        //check if it is a new (last) ring
        if(ringId == mapRings.size()){
            ring.setParticleType(Core.getInstance().getProperties().getLastRingParticles());
            ring.setRingType(RingType.LAST);
            endingRing = ring;
            mapRings.put(ringId,ring);
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "The map " + ChatColor.AQUA + id + ChatColor.GREEN +" has had a new ring added!");
            //Update the ring before it, as it would have been a last ring
            if(ringId-1 != 0){
                Ring oldRing = mapRings.get(ringId-1);
                if(oldRing!= null) {
                    oldRing.setParticleType(Core.getInstance().getProperties().getDefaultRingParticles());
                    oldRing.setRingType(RingType.NORMAL);
                }else{
                    if(ringId == 1){
                        p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "You have set a ring, please remember to set ring 0 the starting ring though!");
                    }else{
                        p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "You have set a ring, although it looks as if you have missed one out!");
                    }
                }
            }
            return;
        }

        if(mapRings.containsKey(ringId)){
            mapRings.remove(ringId);
            ring.setParticleType(Core.getInstance().getProperties().getDefaultRingParticles());
            ring.setRingType(RingType.NORMAL);
            mapRings.put(ringId,ring);
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.GREEN + "The previous ring has been replaced!");
            return;
        }


        //Check if they are added a random ring far in the distance
        if(ringId > mapRings.size()){
            ring.setParticleType(Core.getInstance().getProperties().getLastRingParticles());
            ring.setRingType(RingType.LAST);
            endingRing = ring;
            mapRings.put(ringId,ring);
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED +"NOTE: " + ChatColor.GREEN + " The map " + ChatColor.AQUA + id + ChatColor.GREEN +" has had a new ring added " + ChatColor.RED +"(Although it looks as if you have skipped a ring)");
            if(mapRings.get(mapRings.size()) != null && mapRings.get(mapRings.size()).getNumberID() != 0){
                Ring oldRing = mapRings.get(mapRings.size());
                oldRing.setParticleType(Core.getInstance().getProperties().getDefaultRingParticles());
                oldRing.setRingType(RingType.NORMAL);
            }
            return;
        }



    }

    public Ring getStartingRing() {
        return startingRing;
    }

    public void setStartingRing(Ring startingRing) {
        this.startingRing = startingRing;
    }

    public Ring getEndingRing() {
        return endingRing;
    }

    public void setEndingRing(Ring endingRing) {
        this.endingRing = endingRing;
    }

    public String getId() {
        return id;
    }

    public boolean isComplete(){
        return startingRing != null && endingRing != null;
    }

    public HashMap<Integer, Ring> getMapRings() {
        return mapRings;
    }

    public void setMapRings(HashMap<Integer, Ring> mapRings) {
        this.mapRings = mapRings;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
}
