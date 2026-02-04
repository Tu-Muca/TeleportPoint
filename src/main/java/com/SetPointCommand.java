package com;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c❌ 只有玩家才能设置传送点！");
            return true;
        }

        // 2. 判断是否有权限（OP默认拥有）
        if (!player.hasPermission("teleportpoints.use") && !player.isOp()) {
            player.sendMessage("§c❌ 你没有使用该命令的权限！");
            return true;
        }

        // 3. 判断参数是否完整
        if (args.length == 0) {
            player.sendMessage("§c❌ 用法错误！正确格式：/setpoint <传送点名称>");
            return false; // 触发Bukkit原生用法提示
        }

        // 4. 处理传送点重名提示
        String pointName = args[0];
        Map<String, Location> existingPoints = plugin.getPoints(player.getUniqueId());
        if (existingPoints.containsKey(pointName)) {
            player.sendMessage("§e⚠️  传送点 " + pointName + " 已存在，将覆盖原有位置！");
        }

        // 5. 保存传送点
        Location playerLocation = player.getLocation();
        plugin.addPoint(player.getUniqueId(), pointName, playerLocation);
        player.sendMessage("§a✅ 传送点 " + pointName + " 设置成功！坐标：X=" + playerLocation.getBlockX() + " Y=" + playerLocation.getBlockY() + " Z=" + playerLocation.getBlockZ());

        return true;
    }
}