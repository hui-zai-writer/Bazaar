package com.hui_cat;

import com.hui_cat.event.BossBarEvents;
import com.hui_cat.event.PlayerJoinEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bazaar extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoinEvents(),this );
        getServer().getPluginManager().registerEvents(new BossBarEvents(),this);
        getLogger().info("The plugin has been activated successfully");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("The plugin has been disabled");
    }
}
