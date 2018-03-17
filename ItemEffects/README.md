# ItemEffects
A lightweight plugin allowing an item to contain a potion effect and when clicked, consumed.

View the SpigotMC page to download or view: 
https://www.spigotmc.org/resources/itemeffects-opensource.53314/

# Features:
- Configurable Messages & Titles
- ALL Minecraft potion effects are supported
- Commands supported
- API for developers

# Instructions:
- Place the ItemEffects jar within your /plugins directory
- Start the server, It will work out the box!
- Edit the configuration file to match your likings
- Reload the configuration file by typing /itemeffects reload

# Commands:
- /itemeffect give <player> <effecttype> <level> (Permission: 
itemeffect.give)
- /itemeffect reload (Permission: itemeffect.reload)

# Images:
https://i.imgur.com/998CpuL.png
https://i.imgur.com/3fneNiP.png


# API:
Use Event PlayerConsumeEffectEvent - 
https://github.com/Jet315/ItemEffects/blob/master/src/me/jet315/itemeffects/events/PlayerConsumeEffectEvent.java
This is called when a player consumes an item

Thanks!
