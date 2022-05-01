package com.huicat.events;

import org.apache.commons.io.FileUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;



public class JoinPricerGIver implements Listener {
    SecureRandom randomIntGen = new SecureRandom(); // The Random integer generator
    ItemStack [] joinGivingItem = {
            new ItemStack(Material.DIAMOND_BLOCK , (randomIntGen.nextInt(10086) % 5)), //The Items seed--10086
            new ItemStack(Material.DIAMOND , (randomIntGen.nextInt(10086) % 10)),
            new ItemStack(Material.GOLD_INGOT , (randomIntGen.nextInt(10086) % 25)),
            new ItemStack(Material.GOLD_BLOCK , (randomIntGen.nextInt(10086) % 15)),
            new ItemStack(Material.IRON_BLOCK , (randomIntGen.nextInt(10086) % 30)),
            new ItemStack(Material.IRON_INGOT , (randomIntGen.nextInt(10086) % 50))
    };
    ItemStack shitass = new ItemStack(Material.COCOA);
    @EventHandler
    public void GivePricer(PlayerJoinEvent evt){

    }
    public boolean writePlayersName(String Content){
        String shit;
        try {
            shit = getClass().getClassLoader().getResource("/playersLogged.json").toString();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
        String path = shit.substring(2);
        try {
            FileUtils.writeStringToFile(new File(path) , (Content + "\n") , "UTF-8");
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean readPlayersName(String Content){
        String shit;
        try {
            shit = getClass().getClassLoader().getResource("/playersLogged.json").toString();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
        String path = shit.substring(2);
        String read = null;
        try {
            FileUtils.writeStringToFile(new File(path) , (Content + "\n") , "UTF-8");
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
