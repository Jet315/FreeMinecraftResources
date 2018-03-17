package me.jet315.itemeffects.listeners;

import me.jet315.itemeffects.Core;
import me.jet315.itemeffects.events.PlayerConsumeEffectEvent;
import me.jet315.itemeffects.utils.ItemRenaming;
import me.jet315.itemeffects.utils.Properties;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Jet on 14/02/2018.
 */

public class PlayerConsumeListener implements Listener {

    private Core instance;

    public PlayerConsumeListener(Core instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onClickEvent(PlayerInteractEvent e) {
        //This action is a pressure plate
        if (e.getAction().equals(Action.PHYSICAL)) {

            return;
        }
        //Check to see if right clicking
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            //Check material types match

            if (item.getType() == instance.getProperties().getEffectItem().getType()) {

                //Check it has display name
                if(item.getItemMeta().hasDisplayName()){
                    //Split the display name
                    String[] displayName = item.getItemMeta().getDisplayName().split(" ");
                    //Check length - else likely array error
                    if(displayName.length == 2){
                        //Removes formating
                        displayName[0] = displayName[0].substring(2);

                        String effectName = ItemRenaming.getRealName(displayName[0]);
                        //Get the potion
                        PotionEffectType type = PotionEffectType.getByName(effectName);

                        if(type != null){
                            //We now have a valid potion effect, now we need to get the duration
                            int duration = instance.getProperties().getLevelOne();
                            int levelOfEffect = 1;

                             if(displayName[1].equalsIgnoreCase("ii")){
                                duration = instance.getProperties().getLevelTwo();
                                levelOfEffect = 2;
                            }else if(displayName[1].equalsIgnoreCase("iii")){
                                duration = instance.getProperties().getLevelThree();
                                levelOfEffect = 3;
                            }

                            //Check it is not a fake by ensuring it has a lore
                            if(item.getItemMeta().hasLore()){
                                //Make sure they dont place the block
                                e.setCancelled(true);

                                //All checks are done, it is a valid item - Call the event so other people are able to modify what happens
                                PlayerConsumeEffectEvent takeEffectEvent = new PlayerConsumeEffectEvent(e.getPlayer(),type,levelOfEffect,duration);
                                instance.getServer().getPluginManager().callEvent(takeEffectEvent);

                                //Check it is not cancled
                                if(takeEffectEvent.isCancelled()) return;

                                //Update values as someone may have changed
                                type = takeEffectEvent.getPotionEffectType();
                                levelOfEffect = takeEffectEvent.getLevelOfEffect();
                                duration = takeEffectEvent.getDuration();

                                //Put the effect on the player - Level of effect needs to go -1, starts at 0 for normal
                                PotionEffect effect = new PotionEffect(type,duration*20,levelOfEffect-1,false,instance.getProperties().isshowParticles());
                                e.getPlayer().addPotionEffect(effect);

                                //Send player a formated message
                                String message = ChatColor.translateAlternateColorCodes('&',instance.getProperties().getOnEffectTakenMessage());
                                //Add the formatting
                                String formatedMessage = message.replaceAll("%POTIONEFFECT%",displayName[0].substring(0, 1).toUpperCase() + displayName[0].substring(1)).replaceAll("%LEVEL%",String.valueOf(levelOfEffect)).replaceAll("%DURATION%",String.valueOf(duration)).replaceAll("%PLAYER%",e.getPlayer().getName());
                                e.getPlayer().sendMessage(formatedMessage);

                                //Send player a title if required
                                if(instance.getProperties().isUseTitles()){
                                    //Format the two titles
                                    String mainTitle = ChatColor.translateAlternateColorCodes('&',instance.getProperties().getTitle());
                                    String subTitle = ChatColor.translateAlternateColorCodes('&',instance.getProperties().getSubTitle());
                                    String formatedTitle = mainTitle.replaceAll("%POTIONEFFECT%",displayName[0]).replaceAll("%LEVEL%",String.valueOf(levelOfEffect)).replaceAll("%DURATION%",String.valueOf(duration)).replaceAll("%PLAYER%",e.getPlayer().getName());
                                    String formatedSubTitle =  subTitle.replaceAll("%POTIONEFFECT%",displayName[0]).replaceAll("%LEVEL%",String.valueOf(levelOfEffect)).replaceAll("%DURATION%",String.valueOf(duration)).replaceAll("%PLAYER%",e.getPlayer().getName());
                                    e.getPlayer().sendTitle(formatedTitle,formatedSubTitle,15,40,15);
                                }

                                //Take the item from the player
                                item.setAmount(item.getAmount()-1);
                            }

                        }


                    }
                }
            }

        }
    }
}
