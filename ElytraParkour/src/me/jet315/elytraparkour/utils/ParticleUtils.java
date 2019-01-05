package me.jet315.elytraparkour.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class ParticleUtils {


    public static ArrayList<Location> generateCircleLocations(Location center, double radius, int amount) {

        double increment = (2 * Math.PI) / amount;

        ArrayList<Location> locations = new ArrayList<>();

        //Make the area center the player
        center.setY(center.getY()+1);


        double xangle;
        xangle = Math.toRadians(center.getPitch()-90);

        double xAxisCos = Math.cos(xangle);
        double xAxisSin = Math.sin(xangle);




        //All we need to do to the Y rotation is to add 180
        double yangle = Math.toRadians(center.getYaw()-90);

        double yAxisCos = Math.cos(-yangle);
        double yAxisSin = Math.sin(-yangle);



        double zangle;
        zangle = Math.toRadians(center.getPitch()-90);

        if(zangle < -(Math.PI/2)){
            zangle = -zangle;
        }
        double zAxisCos = Math.cos(zangle); // getting the cos value for the roll.
        double zAxisSin = Math.sin(zangle); // getting the sin value for the roll.


        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = (radius * Math.cos(angle));
            double z = (radius * Math.sin(angle));
            Vector vec = new Vector(x, 0, z);

            vec = rotateAroundAxisZ(vec, zAxisCos, zAxisSin);
            vec = rotateAroundAxisX(vec, xAxisCos, xAxisSin);
            vec = rotateAroundAxisY(vec, yAxisCos, yAxisSin);

            center.add(vec);

            Location loc = new Location(center.getWorld(),center.getX(),center.getY(),center.getZ(),center.getYaw(),center.getPitch());

            locations.add(loc);

            center.subtract(vec);

        }

        return locations;
    }


    /**
     * Methods to rotate around a certain axis (from here - https://www.spigotmc.org/threads/rotating-particle-effects.166854/)
     */

    private static Vector rotateAroundAxisX(Vector v, double cos, double sin) {
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    private static Vector rotateAroundAxisY(Vector v, double cos, double sin) {
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    private static Vector rotateAroundAxisZ(Vector v, double cos, double sin) {
        double x = v.getX() * cos - v.getY() * sin;
        double y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }
}
