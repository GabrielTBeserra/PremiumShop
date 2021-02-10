package br.com.fallcraft.premiumshop.entity.menus;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.data.ShopData;
import br.com.fallcraft.premiumshop.entity.Item;
import br.com.fallcraft.premiumshop.entity.Menu;
import br.com.fallcraft.premiumshop.utils.Ultilities;
import com.quantum.qcoin.api.QCoinAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfirmBuy extends Menu {
    private final int id;
    private final Player player;

    public ConfirmBuy(int id, Player player) {
        super(27, Ultilities.formater("&aConfirmar compra"));
        this.id = id;
        this.player = player;

        Inventory inv = getInventory();

        ItemStack item = new ItemStack(Material.WOOL);
        item.setDurability((short) 5);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Ultilities.formater("&a&lConfirmar"));
        item.setItemMeta(meta);
        inv.setItem(11, item);

        ItemStack item2 = new ItemStack(Material.WOOL);
        item2.setDurability((short) 14);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(Ultilities.formater("&c&lCancelar"));
        item2.setItemMeta(meta2);
        inv.setItem(15, item2);

    }

    public void open() {
        this.player.openInventory(getInventory());
    }


    @Override
    public void handleMenu(InventoryClickEvent e) {
        ShopData data = new ShopData();
        Item item = data.getItem(this.id);

        Player p = (Player) e.getWhoClicked();

        String cmd = item.getCommnad();
        cmd = cmd.replaceAll("@player", p.getName());

        String striped = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());

        if (striped.equalsIgnoreCase("confirmar")) {
            p.sendMessage(Ultilities.formater("&aItem " + item.getTitle() + " &acomprado com sucesso!"));
            QCoinAPI.takeCoin(p, item.getPrice());
            Bukkit.dispatchCommand(PremiumShop.plugin.getServer().getConsoleSender(), cmd);
        }
        p.closeInventory();
    }
}
