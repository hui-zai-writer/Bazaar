package com.hui_cat.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getLogger;


public class BossBarEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void initBar (PlayerJoinEvent event){
        Location loc = event.getPlayer().getLocation();
        BossBar bar = Bukkit.createBossBar("Verifying your ID..." , BarColor.BLUE , BarStyle.SEGMENTED_20 , BarFlag.PLAY_BOSS_MUSIC);
        bar.addPlayer(event.getPlayer());
        for (double i=0;i<=1;i+=0.05){
            event.getPlayer().teleport(loc);
            bar.setProgress(i);
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException e){
                getLogger().info("[Error] Thread has been interrupted!");
                getLogger().info(e.getMessage());
            }
        }
    }
}
