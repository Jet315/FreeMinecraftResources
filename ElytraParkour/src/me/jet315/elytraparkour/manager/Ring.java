package me.jet315.elytraparkour.manager;

import me.jet315.elytraparkour.Core;
import me.jet315.elytraparkour.utils.ParticleUtils;
import me.jet315.elytraparkour.utils.RingType;
import org.bukkit.Location;
import org.bukkit.Particle;

import java.util.ArrayList;

public class Ring {

    /**
     * Stores the center location of a ring
     */
    private Location centerOfRing;
    /**
     * Stores the points of the ring
     */
    private ArrayList<Location> diameterOfRing;
    /**
     * Radius of the ring
     */
    private double radius;
    /**
     * Stores the particle type of the ring
     */
    private Particle particleType;

    /**
     * Stores what type the ring is
     */
    private RingType ringType;

    /**
     * Stores the number ID of the ring, corresponds to its position
     */
    private int numberID;


    public Ring(Location centerOfRing, int numberID,double radius){

        this.centerOfRing = centerOfRing;
        this.numberID = numberID;
        this.radius = radius;
        generateDiameterLocations();
    }

    private void generateDiameterLocations(){

        diameterOfRing = ParticleUtils.generateCircleLocations(this.centerOfRing,radius, Core.getInstance().getProperties().getNumberOfParticlesToSpawnPerRing());

    }

    public Location getCenterOfRing() {
        return centerOfRing;
    }

    public void setCenterOfRing(Location centerOfRing) {
        this.centerOfRing = centerOfRing;
    }

    public Particle getParticleType() {
        return particleType;
    }

    public void setParticleType(Particle particleType) {
        this.particleType = particleType;
    }

    public RingType getRingType() {
        return ringType;
    }

    public void setRingType(RingType ringType) {
        this.ringType = ringType;
    }

    public int getNumberID() {
        return numberID;
    }

    public ArrayList<Location> getDiameterOfRing() {
        return diameterOfRing;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
