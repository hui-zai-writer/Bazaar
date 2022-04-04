package com.hui_cat.event;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEvents  implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String playerID = event.getPlayer().getName();
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_TRADE, 100 , 30);
        event.getPlayer().sendMessage("Welcome to plugin test "+playerID+"!(This message will be removed after release version of the plugin released)");
    }
}
