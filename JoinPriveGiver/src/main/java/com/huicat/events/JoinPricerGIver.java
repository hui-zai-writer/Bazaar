package com.huicat.events;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getUpdateFolder;


public class JoinPricerGIver implements Listener {

    class SimPlayer{ // A Simple class that we use to read Players
        String playerName = null;
        String playerUUID = null;
    }

    SecureRandom randomIntGen = new SecureRandom(); // The Random integer generator

    ItemStack [] joinGivingItem = {
            new ItemStack(Material.DIAMOND_BLOCK , (randomIntGen.nextInt(10086) % 5)), //The Items seed--10086
            new ItemStack(Material.DIAMOND , (randomIntGen.nextInt(10086) % 10)),
            new ItemStack(Material.GOLD_INGOT , (randomIntGen.nextInt(10086) % 25)),
            new ItemStack(Material.GOLD_BLOCK , (randomIntGen.nextInt(10086) % 15)),
            new ItemStack(Material.IRON_BLOCK , (randomIntGen.nextInt(10086) % 30)),
            new ItemStack(Material.IRON_INGOT , (randomIntGen.nextInt(10086) % 50))
    };

    ItemStack shitass = new ItemStack(Material.COCOA); // 60% chance of getting a piece of shit

    @EventHandler(priority = EventPriority.HIGHEST)
    public void GivePricer(PlayerJoinEvent evt){

    }


    public boolean writePlayersName(Player [] writePlayers){
        try {

            JSONObject signInPlayers = new JSONObject(); // Create the Json object

            for (int i = 0; i <= writePlayers.length ; i++){
                signInPlayers.put("name", writePlayers[i].getName()); // Put the name
                signInPlayers.put("UUID", writePlayers[i].getUniqueId().toString()); // Put the UUID
            }

            File pathFile = new File(getUpdateFolder() + "sign_players.json"); // write as {serverPath}/sign_players.json
            if (!pathFile.getParentFile().exists()) {
                try {
                    pathFile.mkdirs(); // Detect whether the file exists or not
                }
                catch (Exception e){
                    getLogger().info(ChatColor.RED + e.getMessage()); // Output it to the Log
                    e.getStackTrace(); // Print it in an another way
                    return false; // Fail Flag
                }
            }

            Writer writing = new OutputStreamWriter(new FileOutputStream(pathFile), StandardCharsets.UTF_8); // Register the writer
            String stringContent = JSON.toJSONString(signInPlayers); // Convert Fast json object to json string
            writing.write(stringContent); // Write them into file
            writing.flush(); // Flush the Stack
            writing.close(); // Close writer
            return true; // Successful Flag
        }
        catch (Exception e){
            getLogger().info(ChatColor.RED + e.getMessage()); // Output it to the Log
            e.getStackTrace(); // Print it in an another way
            return false; // Fail Flag
        }
    }


    public SimPlayer [] readPlayersName(){
        try{
            JSONObject readPlayers = new JSONObject(); //The Json Object that we are gonna read into

            File pathFile = new File(getUpdateFolder() + "sign_players.json"); // Read the {serverPath}/sign_players.json
            if (!pathFile.exists()){
                return null; // Return null to show it fail to read
            }

            JSONReader reading = new JSONReader(new FileReader(pathFile)); // Register the Json reader class
            reading.startObject(); // Start up the Object
            while (reading.hasNext()){
                String key = reading.readString();
                String object = reading.readString();
                readPlayers.put(key , object);
            }


            SimPlayer [] Players = null;
            try {
                for (int i = 0; i <= Players.length; i++) {
                    Players[i].playerName = readPlayers.getString("name");
                    Players[i].playerUUID = readPlayers.getString("UUID");
                }
            }
            catch (NullPointerException e){
                getLogger().info(ChatColor.RED + e.getMessage()); // Output it to the Log
                e.getStackTrace(); // Print it in an another way
                return null; // Fail Flag
            }
            return Players;
        }
        catch (Exception e){
            getLogger().info(ChatColor.RED + e.getMessage()); // Output it to the Log
            e.getStackTrace(); // Print it in an another way
            return null; // Fail Flag
        }
    }
}
