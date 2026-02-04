package com.tumine.TeleportPoint;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.Map;

public class OpenPanelCommand implements CommandExecutor {

    private final TeleportPointsPlugin plugin;

    public OpenPanelCommand(TeleportPointsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 1. 判断是否为玩家
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c❌ 只有玩家才能打开传送面板！");
            return true;
        }

        // 2. 判断权限
        if (!player.hasPermission("teleportpoints.use") && !player.isOp()) {
            player.sendMessage("§c❌ 你没有使用该命令的权限！");
            return true;
        }

        // 3. 获取玩家传送点
        Map<String, Location> playerPoints = plugin.getPoints(player.getUniqueId());
        if (playerPoints.isEmpty()) {
            player.sendMessage("§c❌ 你还没有设置任何传送点！使用 /setpoint <名称> 添加");
            return true;
        }

        // 4. 动态计算面板大小（9的倍数，最多54格）
        int panelSize = Math.min(((playerPoints.size() + 8) / 9) * 9, 54);
        Inventory teleportPanel = Bukkit.createInventory(null, panelSize, "§6📌 传送点选择");

        // 5. 填充传送点物品
        for (Map.Entry<String, Location> entry : playerPoints.entrySet()) {
            String pointName = entry.getKey();
            Location pointLocation = entry.getValue();

            // 创建图标（纸张）
            ItemStack pointItem = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = pointItem.getItemMeta();
            if (itemMeta == null) continue;

            // 设置物品名称和描述
            itemMeta.setDisplayName("§e传送至：" + pointName);
            itemMeta.setLore(Arrays.asList(
                    "§7坐标：X=" + pointLocation.getBlockX(),
                    "§7      Y=" + pointLocation.getBlockY(),
                    "§7      Z=" + pointLocation.getBlockZ(),
                    "§a点击即可传送"
            ));
            pointItem.setItemMeta(itemMeta);

            // 添加到面板
            teleportPanel.addItem(pointItem);
        }

        // 6. 打开面板给玩家
        player.openInventory(teleportPanel);
        return true;
    }
}