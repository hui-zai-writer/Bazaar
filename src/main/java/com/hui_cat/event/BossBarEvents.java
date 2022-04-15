package com.hui_cat.event;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

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
        if (event.getPlayer().getName().equalsIgnoreCase("LCR")){
            event.getPlayer().kick(Component.text("LCR SB.") , PlayerKickEvent.Cause.ILLEGAL_ACTION);
        }
        if (event.getPlayer().getName().equalsIgnoreCase("LCR_SB")){
            event.getPlayer().sendMessage(ChatColor.DARK_BLUE + "Welcome " + event.getPlayer().getName() + ". Thank you for supporting us to fight against LCR!");
            event.getPlayer().customName(Component.text(ChatColor.GREEN + "[THE NICEST PERSON]" + event.getPlayer().getName()));
            if (!event.getPlayer().isCustomNameVisible()){
                event.getPlayer().setCustomNameVisible(true);
            }
        }
        bar.removePlayer(event.getPlayer());
    }
}
