package com.tumine.TeleportPoint;

import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportPointsPlugin extends JavaPlugin {

    private final Map<UUID, Map<String, Location>> playerPoints = new HashMap<>();

    @Override
    public void onEnable() {
        registerCommand("setpoint", new SetPointCommand(this));
        registerCommand("tppanel", new OpenPanelCommand(this));
        getServer().getPluginManager().registerEvents(new TeleportPanelListener(this), this);
        getLogger().info("§a传送点插件已成功启用！");
    }

    @Override
    public void onDisable() {
        getLogger().info("§c传送点插件已卸载！");
    }

    private void registerCommand(String commandName, CommandExecutor executor) {
        PluginCommand command = getCommand(commandName);
        if (command != null) {
            command.setExecutor(executor);
        } else {
            getLogger().severe("§4命令 " + commandName + " 未在 plugin.yml 中注册！");
        }
    }

    public void addPoint(UUID playerUuid, String pointName, Location location) {
        playerPoints.computeIfAbsent(playerUuid, k -> new HashMap<>()).put(pointName, location);
    }

    public Map<String, Location> getPoints(UUID playerUuid) {
        return new HashMap<>(playerPoints.getOrDefault(playerUuid, Map.of()));
    }

    public void removePoint(UUID playerUuid, String pointName) {
        Map<String, Location> playerPointsMap = playerPoints.get(playerUuid);
        if (playerPointsMap != null) {
            playerPointsMap.remove(pointName);
            if (playerPointsMap.isEmpty()) {
                playerPoints.remove(playerUuid);
            }
        }
    }
}