package me.jet315.elytraparkour.manager;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.utils.ParticleUtils;
import me.jet315.elytraparkour.utils.Properties;
import me.jet315.elytraparkour.utils.RingType;
import me.jet315.elytraparkour.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ElytraManager {

    /**
     * Stores mapID and the ElytraMap Object
     */
    private HashMap<String,ElytraMap> activeMaps = new HashMap<>();
    /**
     * Stores the Player object and the ElytraPlayer Object
     */
    private HashMap<Player,ElytraPlayer> elytraPlayers = new HashMap<>();

    private Core instance;

    public ElytraManager(Core instnace){
        this.instance = instnace;
        loadMapData();
        loadPlayers();
    }



    /**
     * Data which is loaded when the plugin is initialised
     */
    private void loadMapData(){
        Properties properties = instance.getProperties();
        FileConfiguration dataFile = properties.getDataFile();
        if(dataFile.getConfigurationSection("Maps") == null) return;
        for(String map : dataFile.getConfigurationSection("Maps").getKeys(false)){
            ElytraMap elytraMap = new ElytraMap(map);

            String location = dataFile.getString("Maps."+map+".spawnLocation");
            if(location != null){
                elytraMap.setSpawnLocation(Utils.locationFromString(location));
            }

            ArrayList<Ring> ringArrayList = new ArrayList<>();

            for(String ringAsString : dataFile.getStringList("Maps." + map + ".ringLocations")){
                Ring ring = Utils.ringFromConfig(ringAsString);
                ringArrayList.add(ring);
            }
            for(Ring ring : ringArrayList){
                if(ring.getNumberID() == 0){
                    //First ring
                    ring.setRingType(RingType.FIRST);
                    ring.setParticleType(properties.getFirstRingParticles());
                    elytraMap.setStartingRing(ring);
                }else if(ring.getNumberID() == ringArrayList.size()-1){
                    //Last ring
                    ring.setRingType(RingType.LAST);
                    ring.setParticleType(properties.getLastRingParticles());
                    elytraMap.setEndingRing(ring);
                }else{
                    //normal ring
                    ring.setRingType(RingType.NORMAL);
                    ring.setParticleType(properties.getDefaultRingParticles());
                }

                //Load locations (where the particles spawn) for each ring
                elytraMap.getMapRings().put(ring.getNumberID(),ring);
            }

            activeMaps.put(map,elytraMap);

        }
    }

    /**
     *
     * @param name The name of the map
     * @return True if the map was deleted, false if it does not exist
     */
    public boolean deleteMap(String name){
        if(!activeMaps.containsKey(name)){
            return false;
        }

        activeMaps.remove(name);
        instance.getProperties().getDataFile().set("Maps."+ name,null);
        instance.getProperties().saveDataConfig();
        return true;
    }

    private void loadPlayers(){
        for(Player p : Bukkit.getOnlinePlayers()){
            ElytraPlayer elytraPlayer = new ElytraPlayer(p);
            elytraPlayers.put(p,elytraPlayer);

        }
    }

    /**
     * Creates an arena
     * @param name The name of the arena
     * @return True if successful, false if arena already exists
     */
    public boolean createArena(String name){
        if(activeMaps.containsKey(name)){
            return false;
        }
        //Save to config
        instance.getProperties().getDataFile().set("Maps."+ name,null);
        instance.getProperties().getDataFile().set("Maps." + name + ".spawnLocation","null");
        instance.getProperties().getDataFile().set("Maps." + name + ".ringLocations","null");
        instance.getProperties().saveDataConfig();
        //Load into hashmap
        activeMaps.put(name,new ElytraMap(name));
        return true;
    }

    /**
     *
     * @param arenaName The name of the arena
     * @param ring a Ring object
     * @param p The player do did it
     * @return True if the arena exists and if the arena has been added, false if it doesn't
     */
    public void addRing(String arenaName,Ring ring,Player p){
        if(!activeMaps.containsKey(arenaName)){
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + "There is no arena called "+ arenaName);
            return;
        }
        //Load into config
        //check if already exists, if it does delete and replace
        instance.getProperties().removeRingDuplicates(arenaName,ring.getNumberID());
        instance.getProperties().addRingToMap(arenaName,ring);
        instance.getProperties().saveDataConfig();

        //Load ring into arena
        ElytraMap map = activeMaps.get(arenaName);
        map.addRingToMap(p,ring);
    }

    public void setSpawnLocation(String arenaName, Player p){
        Location loc = p.getLocation();
        if(!activeMaps.containsKey(arenaName)){
            p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.RED + "There is no arena called "+ arenaName);
            return;
        }
        //load into config
        instance.getProperties().getDataFile().set("Maps." + arenaName + ".spawnLocation", Utils.locationToString(loc));
        instance.getProperties().saveDataConfig();

        //load into arena
        ElytraMap map = activeMaps.get(arenaName);
        map.setSpawnLocation(loc);
        p.sendMessage(Core.getInstance().getProperties().getPluginsPrefix() + ChatColor.translateAlternateColorCodes('&', "&aSpawn location set at &cX:&b"+loc.getBlockX()+"&c Y:&b"+loc.getBlockY() +" &cZ:&b"+loc.getBlockZ() + " &afor map &c" + arenaName));

    }


    /**
     * Getters for the Hashmaps
     */

    public HashMap<Player, ElytraPlayer> getElytraPlayers() {
        return elytraPlayers;
    }

    public HashMap<String, ElytraMap> getActiveMaps() {
        return activeMaps;
    }
}
