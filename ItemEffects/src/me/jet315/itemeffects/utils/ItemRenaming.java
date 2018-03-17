package me.jet315.itemeffects.utils;

/**
 * Created by Jet on 14/02/2018.
 */
public class ItemRenaming {

    public static String getRealName(String s){

        switch (s.toLowerCase()){
            case "haste":
                return "FAST_DIGGING";
            case "strength":
                return "INCREASE_DAMAGE";
            default:
                return s;
        }

    }

    public static String getLikeName(String s){
        switch (s.toLowerCase()){
            case "fast_digging":
                return "haste";
            case "increase_damage":
                return "strength";
            default:
                return s;
        }
    }
}
