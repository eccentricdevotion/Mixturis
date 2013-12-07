/*
 *  Copyright 2013 eccentric_nz.
 */
package me.eccentric_nz.mixturis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author eccentric_nz
 */
public class MixturisRecipeListener implements Listener {

    Mixturis plugin;

    public MixturisRecipeListener(Mixturis plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRecipeClick(InventoryClickEvent event) {
        Inventory top = event.getView().getTopInventory();
        InventoryType type = top.getType();
        if (type == InventoryType.WORKBENCH) {
            final Player player = (Player) event.getWhoClicked();
            if (plugin.getRecipeViewers().contains(player.getName())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRecipeClose(InventoryCloseEvent event) {
        Inventory top = event.getView().getTopInventory();
        InventoryType type = top.getType();
        if (type == InventoryType.WORKBENCH) {
            Player p = (Player) event.getPlayer();
            String name = p.getName();
            if (plugin.getRecipeViewers().contains(name)) {
                plugin.getRecipeViewers().remove(name);
                event.getView().getTopInventory().clear();
                p.updateInventory();
            }
        }
    }
}
