package me.jet315.elytraparkour.events;

import me.jet315.elytraparkour.manager.Ring;
import me.jet315.elytraparkour.utils.RingType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RingEnterEvent extends Event {

    /**
     * Called when a user first claims a house
     */

    private static final HandlerList handlers = new HandlerList();

    private Player player;

    private RingType ringType;

    private Ring ring;

    private boolean isCancelled = false;

    public RingEnterEvent(Player player,Ring ring){
        this.player = player;
        this.ringType = ring.getRingType();
        this.ring = ring;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return The player entering the loop
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return The ringtype
     */
    public RingType getRingType() {
        return ringType;
    }

    /**
     * @return The ring that they flew through
     */
    public Ring getRing() {
        return ring;
    }
}



//ElytraParkourAPI too?
