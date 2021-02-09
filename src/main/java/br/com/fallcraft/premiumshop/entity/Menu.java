package br.com.fallcraft.premiumshop.entity;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class Menu implements InventoryHolder {
    private final int menuSize;
    private final String name;
    private Inventory inventory;

    public Menu(int menuSize, String name) {
        this.menuSize = menuSize;
        this.name = name;
        inventory = Bukkit.createInventory(this, menuSize, name);
    }

    public int getMenuSize() {
        return menuSize;
    }

    public String getName() {
        return name;
    }

    public abstract void handleMenu(InventoryClickEvent e);

    @Override
    public Inventory getInventory() {
        return inventory;
    }



    public ItemStack makeItem(Material material, String displayName, String... lore) {

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);

        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);

        return item;
    }


}
