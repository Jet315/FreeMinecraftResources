package me.jet315.itemeffects.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;
import org.bukkit.potion.PotionType;

/**
 * Created by Jet on 14/02/2018.
 */
public class PlayerConsumeEffectEvent extends Event implements Cancellable {

    /**
     * Called when a user takes an effect
     */

    private static final HandlerList handlers = new HandlerList();

    private Player player;

    private boolean isCancelled = false;

    private PotionEffectType potionEffectType;
    private int levelOfEffect;
    private int duration;


    public PlayerConsumeEffectEvent(Player player,PotionEffectType effectType,int levelOfEffect, int duration){
        this.player = player;
        this.potionEffectType = effectType;
        this.levelOfEffect = levelOfEffect;
        this.duration = duration;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    /**
     *
     * @return The player involved
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    /**
     *
     * @return The potion effect being used
     */
    public PotionEffectType getPotionEffectType(){
        return this.potionEffectType;
    }

    /**
     *
     * @param effectType The type of potion that is to be used
     */
    public void setPotionEffectType(PotionEffectType effectType){
        this.potionEffectType = effectType;
    }

    /**
     *
     * @return The level of the effect being given
     */
    public int getLevelOfEffect(){
        return this.levelOfEffect;
    }

    /**
     *
     * @param levelOfEffect The level of the potion
     */
    public void setLevelOfEffect(int levelOfEffect){
        this.levelOfEffect = levelOfEffect;
    }

    /**
     *
     * @return The duration in seconds the effect lasts for
     */
    public int getDuration() {
        return duration;
    }

    /**
     *
     * @param duration the amount of time in seconds the effect should last for
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
