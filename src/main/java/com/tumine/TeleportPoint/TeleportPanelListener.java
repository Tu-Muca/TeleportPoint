package com.tumine.TeleportPoint; // 你的包名是com，和文件夹结构一致

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener; // 必须导入Listener
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// 实现Listener接口（必须导入Listener）
//public class TeleportPanelListener implements Listener {
//    private final TeleportPointsPlugin plugin;
//
//    public TeleportPanelListener(TeleportPointsPlugin plugin) {
//        this.plugin = plugin;
//    }
//
//    @EventHandler
//    public void onTeleportPanelClick(InventoryClickEvent event) {
//        if (!event.getView().getTitle().equals("§6📌 传送点选择")) {
//            return;
//        }
//
//        event.setCancelled(true);
//
//        ItemStack clickedItem = event.getCurrentItem();
//        Player player = (Player) event.getWhoClicked();
//
//        if (clickedItem == null || clickedItem.getType().isAir() || !clickedItem.hasItemMeta()) {
//            return;
//        }
//
//        ItemMeta itemMeta = clickedItem.getItemMeta();
//        if (itemMeta == null || !itemMeta.hasDisplayName()) {
//            return;
//        }
//
//        String itemDisplayName = itemMeta.getDisplayName();
//        if (!itemDisplayName.startsWith("§e传送至：")) {
//            return;
//        }
//        String targetPointName = itemDisplayName.replace("§e传送至：", "");
//
//        Location targetLocation = plugin.getPoints(player.getUniqueId()).get(targetPointName);
//        if (targetLocation != null) {
//            player.teleport(targetLocation);
//            player.sendMessage("§a✅ 已传送到 " + targetPointName + "！");
//            player.closeInventory();
//        } else {
//            player.sendMessage("§c❌ 该传送点已被删除！");
//            player.closeInventory();
//        }
//    }
//}
