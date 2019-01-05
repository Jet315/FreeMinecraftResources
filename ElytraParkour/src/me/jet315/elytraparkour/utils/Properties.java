package me.jet315.elytraparkour.utils;

import me.jet315.elytraparkour.Core;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import javax.print.attribute.standard.MediaSize;

public class Properties extends DataFile{

    /**
     * Plugins prefix
     */
    private String pluginsPrefix = "plugins prefix";
    /**
     * The particle types for the different rings
     */
    private Particle firstRingParticles = Particle.VILLAGER_HAPPY;
    private Particle defaultRingParticles = Particle.CLOUD;
    private Particle lastRingParticles = Particle.FLAME;
    private Particle testingRingParticle = Particle.FLAME;
    private int numberOfParticlesToSpawnPerRing = 30;
    private int particleSpawnDelay = 30;
    private int additionalSpawnRings = 0;
    private int additionalPlayerRings = 2;

    /**
     * Boosters
     */
    private double firstRingBoost = 2;
    private double defaultRingBoost = 1;
    private double lastRingBoost = 0;

    private Sound firstRingSound = Sound.BLOCK_NOTE_PLING;
    private Sound defaultRingSound = Sound.BLOCK_NOTE_PLING;
    private Sound lastRingSound = Sound.BLOCK_NOTE_PLING;

    private Particle firstRingFeetParticles = Particle.FLAME;
    private Particle defaultRingFeetParticles = Particle.DRAGON_BREATH;
    private Particle lastRingFeetParticles = Particle.VILLAGER_HAPPY;


    private boolean teleportToMapSpawnAtLastRing = true;
    private String messageToSendWhenReachLastRing = "Fini!";

    private boolean teleportToMapSpawnIfStopsGliding = true;
    private String stopGlidingMessage = "Keep moving!";

    private String noPermissions = "No perms!";

    private String firstRingMessage = "First ring!";

    public Properties(Core instance){
        super(instance);
        loadProperties();
    }

    private void loadProperties(){
        pluginsPrefix = ChatColor.translateAlternateColorCodes('&',config.getString("PluginsPrefix"));

        firstRingParticles = Particle.valueOf(config.getString("FirstRingParticles"));
        defaultRingParticles = Particle.valueOf(config.getString("DefaultRingParticles"));
        lastRingParticles = Particle.valueOf(config.getString("LastRingParticles"));
        testingRingParticle = Particle.valueOf(config.getString("TestingRingParticle"));

        numberOfParticlesToSpawnPerRing = config.getInt("NumberOfParticlesPerRing");
        particleSpawnDelay = config.getInt("SpawningParticlesDelay");
        additionalSpawnRings = config.getInt("AdditionalSpawnRings");

        additionalPlayerRings = config.getInt("RingsInfrontOfPlayer");

        firstRingBoost = config.getDouble("FirstRingBoost");
        defaultRingBoost = config.getDouble("defaultRingBoost");
        lastRingBoost = config.getDouble("lastRingBoost");

        firstRingSound = Sound.valueOf(config.getString("FirstRingSound"));
        defaultRingSound = Sound.valueOf(config.getString("DefaultRingSound"));
        lastRingSound = Sound.valueOf(config.getString("LastRingSound"));

        firstRingFeetParticles = Particle.valueOf(config.getString("FirstRingFeetParticles"));
        defaultRingFeetParticles = Particle.valueOf(config.getString("DefaultRingFeetParticles"));
        lastRingFeetParticles = Particle.valueOf(config.getString("LastRingFeetParticles"));


        teleportToMapSpawnAtLastRing = config.getBoolean("TeleportToMapSpawnAtLastRing");
        messageToSendWhenReachLastRing = ChatColor.translateAlternateColorCodes('&',config.getString("MessageToSendWhenReachedLastRing"));

        teleportToMapSpawnIfStopsGliding = config.getBoolean("TeleportToMapSpawnIfStopsGliding");
        stopGlidingMessage = ChatColor.translateAlternateColorCodes('&',config.getString("StopGlidingMessage"));

        noPermissions = ChatColor.translateAlternateColorCodes('&',config.getString("NoPermissions"));

        firstRingMessage = ChatColor.translateAlternateColorCodes('&',config.getString("FirstRingMessage"));

    }

    /**
     * @returns the config.yml configuration
     */
    public FileConfiguration getPropertiesFile(){
        return instance.getConfig();
    }

    public Particle getFirstRingParticles() {
        return firstRingParticles;
    }

    public Particle getDefaultRingParticles() {
        return defaultRingParticles;
    }

    public Particle getLastRingParticles() {
        return lastRingParticles;
    }

    public int getNumberOfParticlesToSpawnPerRing() {
        return numberOfParticlesToSpawnPerRing;
    }

    public int getParticleSpawnDelay() {
        return particleSpawnDelay;
    }

    public int getAdditionalSpawnRings() {
        return additionalSpawnRings;
    }

    public int getAdditionalPlayerRings() {
        return additionalPlayerRings;
    }

    public Particle getTestingRingParticle() {
        return testingRingParticle;
    }

    public String getPluginsPrefix() {
        return pluginsPrefix;
    }

    public double getFirstRingBoost() {
        return firstRingBoost;
    }

    public double getDefaultRingBoost() {
        return defaultRingBoost;
    }

    public double getLastRingBoost() {
        return lastRingBoost;
    }

    public boolean isTeleportToMapSpawnAtLastRing() {
        return teleportToMapSpawnAtLastRing;
    }

    public Sound getFirstRingSound() {
        return firstRingSound;
    }

    public Sound getDefaultRingSound() {
        return defaultRingSound;
    }

    public Sound getLastRingSound() {
        return lastRingSound;
    }

    public Particle getFirstRingFeetParticles() {
        return firstRingFeetParticles;
    }

    public Particle getDefaultRingFeetParticles() {
        return defaultRingFeetParticles;
    }

    public Particle getLastRingFeetParticles() {
        return lastRingFeetParticles;
    }

    public boolean isTeleportToMapSpawnIfStopsGliding() {
        return teleportToMapSpawnIfStopsGliding;
    }

    public String getMessageToSendWhenReachLastRing() {
        return messageToSendWhenReachLastRing;
    }

    public String getStopGlidingMessage() {
        return stopGlidingMessage;
    }

    public String getNoPermissions() {
        return noPermissions;
    }

    public String getFirstRingMessage() {
        return firstRingMessage;
    }
}
