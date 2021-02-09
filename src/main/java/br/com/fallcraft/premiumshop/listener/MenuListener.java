package br.com.fallcraft.premiumshop.listener;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.entity.Menu;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {
    private final PremiumShop premiumShop;

    public MenuListener(PremiumShop premiumShop) {
        this.premiumShop = premiumShop;
        this.premiumShop.getServer().getPluginManager().registerEvents(this, premiumShop);
    }


    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType().equals(Material.AIR)) return;

            Menu menu = (Menu) holder;
            menu.handleMenu(e);
        }

    }
}
