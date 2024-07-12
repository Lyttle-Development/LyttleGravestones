package com.lyttledev.lyttlegravestone.listeners;

import com.lyttledev.lyttlegravestone.LyttleGravestone;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryDrag implements Listener {

    public InventoryDrag(LyttleGravestone plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        String title = event.getView().title().toString();
        if (!title.contains("'s gravestone")) { return; }

        ItemStack draggedItem = event.getOldCursor();
        if (draggedItem.getType() == Material.AIR) { return; }

        int inventorySize = event.getInventory().getSize();
        for (int slot : event.getRawSlots()) {
            if (slot < inventorySize) {
                event.setCancelled(true);
                break;
            }
        }
    }

}