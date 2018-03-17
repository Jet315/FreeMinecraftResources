package me.jet315.itemeffects;

import me.jet315.itemeffects.commands.CommandHandler;
import me.jet315.itemeffects.listeners.PlayerConsumeListener;
import me.jet315.itemeffects.utils.Properties;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jet on 14/02/2018.
 */
public class Core extends JavaPlugin {

    //Properties
    private Properties properties;

    public void onEnable(){
        //Load configuration file properties
        properties = new Properties(this);

        //Register Listeners
        Bukkit.getPluginManager().registerEvents(new PlayerConsumeListener(this),this);

        //Register commands
        getCommand("itemeffect").setExecutor(new CommandHandler(this));

    }


    public void onDisable(){

    }

    public void reloadProperties(){
        this.properties = null;
        this.reloadConfig();
        this.properties = new Properties(this);
    }


    public Properties getProperties() {
        return properties;
    }
}
