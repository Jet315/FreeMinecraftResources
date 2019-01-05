package me.jet315.elytraparkour.utils;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.manager.ElytraMap;
import me.jet315.elytraparkour.manager.Ring;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class DataFile {

    /**
     * Sub classes needs to access these properties
     */
    protected Core instance;

    protected FileConfiguration dataFileConfig;
    private File dataFile;

    protected FileConfiguration config;

    public DataFile(Core instance){
        this.instance = instance;
        createFiles();
        loadDataConfig();
        loadConfig();
    }

    private void createFiles(){
        try {
            //Ensure directory exists
            if (!instance.getDataFolder().exists()) {
                instance.getDataFolder().mkdirs();
            }
            //Creating the config.yml
            File file = new File(instance.getDataFolder(), "config.yml");
            if (!file.exists()) {
                instance.saveResource("config.yml",false);
            }

            //Creating the data.yml
            File dataFile = new File(instance.getDataFolder(), "data.yml");
            if(!dataFile.exists()){
                instance.saveResource("data.yml",false);
            }
            this.dataFile = dataFile;

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void loadDataConfig(){
        dataFileConfig = YamlConfiguration.loadConfiguration(new File(instance.getDataFolder(),"data.yml"));
    }
    private void loadConfig(){
        this.config = instance.getConfig();
    }

    public void saveDataConfig(){
        try {
            instance.saveConfig();
            this.dataFileConfig.save(dataFile);
        } catch (IOException var2) {
            System.out.println("Could not save config to " + dataFile.getName());

        }
    }

    /**
     * Checks to see whether a map and ring is already in the configuration file, and if it does, will remove it
     */
    public boolean removeRingDuplicates(String arenaName,int ringID){
        for(String map : dataFileConfig.getConfigurationSection("Maps").getKeys(false)){
            if(map.equalsIgnoreCase(arenaName)) {
                List<String> rings = dataFileConfig.getStringList("Maps." + map + ".ringLocations");
                for (String ringAsString : rings) {
                    if(Integer.valueOf(ringAsString.split(",")[6]) == ringID){
                        rings.remove(ringAsString);
                        dataFileConfig.set("Maps."+map+".ringLocations",rings);
                        this.saveDataConfig();
                        return true;
                    }
                }
            }
            }
            return false;
    }

    /**
     * Adds a new ring to the DataFile
     * @return true if added ring, false otherwise
     */
    public boolean addRingToMap(String arenaName,Ring ring){
        String ringAsString = Utils.ringToString(ring);
        for(String map : dataFileConfig.getConfigurationSection("Maps").getKeys(false)){
            if(map.equalsIgnoreCase(arenaName)){
                List<String> list = dataFileConfig.getStringList("Maps."+map+".ringLocations");
                list.add(ringAsString);
                dataFileConfig.set("Maps."+map+".ringLocations",list);
                return true;
            }
        }
        return false;
    }

    public FileConfiguration getDataFile() {
        return dataFileConfig;
    }
}
