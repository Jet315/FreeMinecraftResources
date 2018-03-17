package me.jet.cps;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Properties {

    /**
     * Stores the plugin instance
     */
    private CPS instance;

    /**
     * Stores the configuration object
     */
    private FileConfiguration config;

    /**
     * Whether the action bar is hidden if CPS is 0
     */
    public boolean hideActionBar = false;

    /**
     *The various messages
     */
    private String actionBarText;
    private String startCPSCheck;
    private String cpsCheckStart;
    private String cpsMessage;
    private String noPermission;

    private String otherCPSCheck;
    private String otherCPSCheckStart;
    private String otherCPSCheckNoPermission;
    private String playerNotFound;
    private String invalidArgs;
    private String playerLoggedOut;
    private String otherCPSMessage;


    public Properties(CPS instance){
        this.instance = instance;
        this.config = instance.getConfig();
        loadConfig();
    }


    public void loadConfig(){
        createConfig();
        hideActionBar = config.getBoolean("HideActionBar");

        actionBarText = ChatColor.translateAlternateColorCodes('&',config.getString("ActionBarText"));
        startCPSCheck = ChatColor.translateAlternateColorCodes('&',config.getString("StartCPSCheck"));
        cpsCheckStart = ChatColor.translateAlternateColorCodes('&',config.getString("CPSCheckStart"));
        cpsMessage = ChatColor.translateAlternateColorCodes('&',config.getString("CPSMessage"));
        noPermission = ChatColor.translateAlternateColorCodes('&',config.getString("NoPermission"));

        otherCPSCheck = ChatColor.translateAlternateColorCodes('&',config.getString("OtherCPSCheck"));
        otherCPSCheckStart = ChatColor.translateAlternateColorCodes('&',config.getString("OtherCPSCheckStart"));
        otherCPSCheckNoPermission = ChatColor.translateAlternateColorCodes('&',config.getString("OtherCPSCheckNoPermission"));
        playerNotFound = ChatColor.translateAlternateColorCodes('&',config.getString("PlayerNotFound"));
        invalidArgs = ChatColor.translateAlternateColorCodes('&',config.getString("InvalidArgs"));
        playerLoggedOut = ChatColor.translateAlternateColorCodes('&',config.getString("PlayerLoggedOut"));
        otherCPSMessage = ChatColor.translateAlternateColorCodes('&',config.getString("OtherCPSMessage"));
    }

    /**
     * Creates the config if it does not exist
     */
    private void createConfig() {
        try {
            if (!instance.getDataFolder().exists()) {
                instance.getDataFolder().mkdirs();
            }
            File file = new File(instance.getDataFolder(), "config.yml");
            if (!file.exists()) {
                instance.getLogger().info("Config.yml not found, creating!");
                instance.saveDefaultConfig();
            } else {
                instance.getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * The getters for the fields
     */

    public String getActionBarText() {
        return actionBarText;
    }

    public String getStartCPSCheck() {
        return startCPSCheck;
    }

    public String getCpsCheckStart() {
        return cpsCheckStart;
    }

    public String getCpsMessage() {
        return cpsMessage;
    }

    public String getNoPermission() {
        return noPermission;
    }

    public String getOtherCPSCheck() {
        return otherCPSCheck;
    }

    public String getOtherCPSCheckStart() {
        return otherCPSCheckStart;
    }

    public String getOtherCPSCheckNoPermission() {
        return otherCPSCheckNoPermission;
    }

    public String getPlayerNotFound() {
        return playerNotFound;
    }

    public String getInvalidArgs() {
        return invalidArgs;
    }

    public String getPlayerLoggedOut() {
        return playerLoggedOut;
    }

    public String getOtherCPSMessage() {
        return otherCPSMessage;
    }



}
