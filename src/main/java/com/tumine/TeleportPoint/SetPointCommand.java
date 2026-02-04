package com.tumine.TeleportPoint;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Map; // 导入Map类
public class SetPointCommand implements CommandExecutor {

    private final TeleportPointsPlugin plugin;

    // 构造函数注入主类实例
    public SetPointCommand(TeleportPointsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 1. 判断是否为玩家
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c❌ 只有玩家才能设置传送点！");
            return true;
        }

        Player player = (Player) sender;

        // 2. 判断是否有权限（OP默认拥有）
        if (!player.hasPermission("teleportpoints.command.set") && !player.isOp()) {
            player.sendMessage("§c❌ 你没有使用该命令的权限！");
            return true;
        }

        // 3. 判断参数是否完整
        if (args.length == 0) {
            player.sendMessage("§c❌ 用法错误！正确格式：/setpoint <传送点名称>");
            return false; // 触发Bukkit原生用法提示
        }


        switch (args[0]){
            case "create":
                create(player,args[1]);
                break;
        }
        return false;
    }

    public void create(Player player,String name){
        TeleportPointsPlugin.YamlPointMap.set(name+ ".owner",player.getName());
        TeleportPointsPlugin.YamlPointMap.set(name+ ".location",player.getLocation().toString());
        try {
            TeleportPointsPlugin.YamlPointMap.save(TeleportPointsPlugin.PointMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}