package me.jet315.elytraparkour.manager;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.utils.Properties;
import me.jet315.elytraparkour.utils.RingType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ParticleManager {

    private Core instance;
    private ElytraManager elytraManager;
    private Properties properties;

    public ParticleManager(Core instance){
        this.instance = instance;
        this.elytraManager = instance.getElytraManager();
        this.properties = instance.getProperties();
        spawnFirstRings();
        spawnPlayerRings();
    }

    //TODO: Currently quite server heavy.. Need to spread out spawning particle effects across multiple ticks
    public void spawnFirstRings() {
        Bukkit.getServer().getScheduler().runTaskTimer(instance, new Runnable() {
            @Override
            public void run() {
                HashMap<World, Boolean> needToSpawnFirstRing = new HashMap<World, Boolean>();

                for (ElytraMap map : elytraManager.getActiveMaps().values()) {
                    if(!map.isComplete()){
                        for(Ring ring : map.getMapRings().values()){
                            for (Location l : ring.getDiameterOfRing()) {
                                l.getWorld().spawnParticle(ring.getParticleType(), l,1,0,0,0,0,null);
                            }
                        }
                        continue;
                    }
                    //Get the world of the map
                    World world = map.getStartingRing().getCenterOfRing().getWorld();

                    //Check if array contains the world (whether the check has been performed to check whether the first rings need to be spawned)
                    if (needToSpawnFirstRing.containsKey(world)) {
                        if (!needToSpawnFirstRing.get(world)) {
                            continue;
                        }
                    } else {
                        if (world.getPlayers().size() <= 4) {
                            boolean isAllInCourse = true;
                            INNER:
                            for (Player p : world.getPlayers()) {
                                if (elytraManager.getElytraPlayers().containsKey(p) && !elytraManager.getElytraPlayers().get(p).isInMap()) {
                                    isAllInCourse = false;
                                    break INNER;
                                }
                            }
                            if (isAllInCourse) {
                                needToSpawnFirstRing.put(world, false);
                                continue;
                            }
                        } else {
                            needToSpawnFirstRing.put(world, true);
                        }
                    }
                    //First rings need to be spawned

                    for (Location l : map.getStartingRing().getDiameterOfRing()) {
                        world.spawnParticle(properties.getFirstRingParticles(), l,1,0,0,0,0,null);
                    }
                    //Check if any more rings need to be spawned
                    if (properties.getAdditionalSpawnRings() != 0) {
                        for (int i = 0; i < properties.getAdditionalSpawnRings(); i++) {
                            Ring ring = map.getMapRings().get(i + 1);
                            if (ring != null) {
                                if (ring.getRingType() == RingType.LAST) {
                                    for (Location l : ring.getDiameterOfRing()) {
                                        world.spawnParticle(ring.getParticleType(), l,1,0,0,0,0,null);
                                    }
                                } else {
                                    for (Location l : ring.getDiameterOfRing()) {
                                        world.spawnParticle(ring.getParticleType(), l,1,0,0,0,0,null);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }, 120L, instance.getProperties().getParticleSpawnDelay());
    }

        //TODO: Currently quite server heavy.. Need to spread out spawning particle effects across multiple ticks
        public void spawnPlayerRings () {
        final int ringsToSpawnAhead = properties.getAdditionalPlayerRings();
        final int spawnRings = properties.getAdditionalSpawnRings();

            Bukkit.getServer().getScheduler().runTaskTimer(instance, new Runnable() {
                @Override
                public void run() {
                    //Stores already spawned in rings (Map object, followed by the ring number)
                    HashMap<ElytraMap,Integer> spawnedInRings = new HashMap<>();

                    for(Player p : elytraManager.getElytraPlayers().keySet()){
                        if(p.isGliding() && elytraManager.getElytraPlayers().get(p).isInMap()){
                            ElytraMap map = elytraManager.getElytraPlayers().get(p).getMap();
                            int currentRing = elytraManager.getElytraPlayers().get(p).getRingNumber();

                            for(int i = currentRing+1 ; i < (currentRing+ringsToSpawnAhead)+1; i ++){
                                if((i-currentRing-1) >= spawnRings){

                                  continue;
                                }else{
                                    //Check if already spawned by another player
                                    if(spawnedInRings.containsKey(map)){
                                        if(spawnedInRings.get(map) == i){

                                            continue;
                                        }
                                    }

                                    //Add ring to spawned rings
                                    spawnedInRings.put(map,i );
                                    //spawn ring (if not null)
                                    Ring ring = map.getMapRings().get(i);
                                    if(ring != null){

                                            for (Location l : ring.getDiameterOfRing()) {
                                                l.getWorld().spawnParticle(ring.getParticleType(), l,1,0,0,0,0,null);
                                            }

                                        }
                                    }
                                }


                        }
                    }
                }
            }, 120L, instance.getProperties().getParticleSpawnDelay()/2);
        }
    }

