package com.hui_cat.event;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
            Player lcrsbPlayer = event.getPlayer();
            PlayerInventory joinPriceGiver = event.getPlayer().getInventory();
            ItemStack diamondBlocks = new ItemStack(Material.DIAMOND_BLOCK , 64);
            if (joinPriceGiver.contains(Material.COCOA_BEANS)) {
                lcrsbPlayer.sendMessage(Component.text(ChatColor.GOLD + "No! There are some shit in your inventory!\nI will clear it now."));
                joinPriceGiver.setItem(Material.COCOA_BEANS.getEquipmentSlot() , diamondBlocks);
            }
            joinPriceGiver.addItem(diamondBlocks);
            joinPriceGiver.addItem(diamondBlocks);
            ItemStack [] armorsOfHero = joinPriceGiver.getArmorContents();
            ItemStack [] armorsGift = {new ItemStack(Material.NETHERITE_HELMET),new ItemStack(Material.NETHERITE_CHESTPLATE),new ItemStack(Material.NETHERITE_LEGGINGS),new ItemStack(Material.NETHERITE_BOOTS)};
            joinPriceGiver.setArmorContents(armorsGift);
            joinPriceGiver.addItem(armorsOfHero);
            lcrsbPlayer.sendMessage(Component.text(ChatColor.GOLD + "Your original armor has been added into your inventory.\nYou can check it now."));

        }
        bar.removePlayer(event.getPlayer());
    }
}
