package com.yourdomain.Main;

import com.yourdomain.ClickToPutThings.ClickToPutThings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[ClickToPutThings] this plugin is starting");
        PluginManager pluginManager = Bukkit.getPluginManager();
        // 正确注册事件监听器
        pluginManager.registerEvents(new ClickToPutThings(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[ClickToPutThings] this plugin is stopping");
    }
}