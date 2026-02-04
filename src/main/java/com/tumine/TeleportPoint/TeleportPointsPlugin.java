package com.tumine.TeleportPoint;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportPointsPlugin extends JavaPlugin {
    public static File PointMap = new File("PointMap.yml");
    public static YamlConfiguration YamlPointMap = YamlConfiguration.loadConfiguration(PointMap);

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("TeleportPoint").setExecutor(new TeleportPointsPlugin());
//        getServer().getPluginManager().registerEvents(new TeleportPanelListener(this), this);
        getLogger().info("§a传送点插件已成功启用！");
    }

//    public void addPoint(UUID playerUuid, String pointName, Location location) {
//        playerPoints.computeIfAbsent(playerUuid, k -> new HashMap<>()).put(pointName, location);
//    }
//
//    public Map<String, Location> getPoints(UUID playerUuid) {
//        return new HashMap<>(playerPoints.getOrDefault(playerUuid, Map.of()));
//    }
//
//    public void removePoint(UUID playerUuid, String pointName) {
//        Map<String, Location> playerPointsMap = playerPoints.get(playerUuid);
//        if (playerPointsMap != null) {
//            playerPointsMap.remove(pointName);
//            if (playerPointsMap.isEmpty()) {
//                playerPoints.remove(playerUuid);
//            }
//        }
//    }
}