package me.jet315.itemeffects.utils;

import me.jet315.itemeffects.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Jet on 14/02/2018.
 */
public class Properties {

    /**
     * Stores properties of the plugin
     */

    /**
     * Stores the main instance
     */
    private Core instance;

    /**
     * Stores the Material that is used to give enchantments on
     */
    private ItemStack effectItem = new ItemStack(Material.CLAY,1);

    /**
     * Stores the times that the effect is given (in seconds) for a particular level
     */
    private int levelOne = 60;
    private int levelTwo = 120;
    private int levelThree = 180;

    /**
     * Boolean that stores whether particles should show when a user consumes an effect
     */
    private boolean showParticles = true;

    /**
     * Stores messages that are displayed to the user when they take an effect
     * Place holders include:
     # %POTIONEFFECT% - Is replaced for the type of effect that is being activated (I.E Strength)
     # %LEVEL% - Is replaced for the potion effects level
     # %DURATION% - The time (in seconds) the effect is present for
     # %PLAYER% - The player's name
     */

    //Whether or not Titles (a 1.8+ Minecraft Feature that displays text accross the screen) should be used
    private boolean useTitles = true;
    //Main title / subtitle messages
    private String title = "&f&l+ &c%POTIONEFFECT%";
    private String subTitle = "&a&m----------------------";

    //Text messages
    private String onEffectTakenMessage = "&6Effects &e&l> &cYou have activated &a%POTIONEFFECT%&c for &a%DURATION% &cseconds!";


    //Constructor
    public Properties(Core instance){
        this.instance = instance;
        this.loadMessages();
    }



    public void loadMessages(){
        createConfig();
        FileConfiguration config = instance.getConfig();

        //Values from config file
        effectItem = new ItemStack(Material.valueOf(config.getString("EffectItem")),1);

        levelOne = config.getInt("TimeForLevel1");
        levelTwo = config.getInt("TimeForLevel2");
        levelThree = config.getInt("TimeForLevel3");

        showParticles = config.getBoolean("ShowParticles");

        useTitles = config.getBoolean("UseTitles");
        title = config.getString("MainTitle");
        subTitle = config.getString("SubTitle");

        onEffectTakenMessage = config.getString("OnEffectTaken");

        //The meta for the effect item
        ItemMeta effectMeta = effectItem.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Right-Click this item");
        lore.add(ChatColor.GREEN + "to consume!");
        effectMeta.setLore(lore);
        effectItem.setItemMeta(effectMeta);
    }

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


    public ItemStack getEffectItem() {
        return effectItem;
    }


    public int getLevelOne() {
        return levelOne;
    }


    public int getLevelTwo() {
        return levelTwo;
    }

    public int getLevelThree() {
        return levelThree;
    }

    public boolean isUseTitles() {
        return useTitles;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getOnEffectTakenMessage() {
        return onEffectTakenMessage;
    }


    public boolean isshowParticles() {
        return showParticles;
    }
}
