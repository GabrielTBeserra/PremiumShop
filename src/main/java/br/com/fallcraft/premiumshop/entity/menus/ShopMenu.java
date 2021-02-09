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

import java.util.ArrayList;
import java.util.List;

public class ShopMenu extends Menu {
    private final Player player;

    public ShopMenu(Player player) {
        super(54, Ultilities.formater("&6&lPremiumShop"));
        this.player = player;

        Inventory inv = getInventory();


        ShopData shopData = new ShopData();

        for (Item i : shopData.getItens()) {
            ItemStack item = new ItemStack(Material.getMaterial(i.getBlockId()));
            item.setDurability(Short.parseShort(i.getType()));

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Ultilities.formater(i.getTitle()));
            List<String> lore = new ArrayList<>();
            lore.add(Ultilities.formater("&2&lPreco: &6" + i.getPrice() + "&9 Coins"));
            lore.add(Ultilities.formater("&b&lID: &6" + i.getId()));
            meta.setLore(lore);

            item.setItemMeta(meta);

            inv.addItem(item);
        }


    }

    public void open() {
        this.player.openInventory(getInventory());
    }


    @Override
    public void handleMenu(InventoryClickEvent e) {
        ShopData data = new ShopData();


        String[] striped = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(1)).split(" ");


        Item item = data.getItem(Integer.parseInt(striped[1]));

        Player p = (Player) e.getWhoClicked();

        String cmd = item.getCommnad();
        cmd = cmd.replaceAll("@player", p.getName());


        if (QCoinAPI.getCoin(p) >= item.getPrice()) {
            p.sendMessage(Ultilities.formater("&aItem " + item.getTitle() + " &acomprado com sucesso!"));
            QCoinAPI.takeCoin(p, item.getPrice());
            Bukkit.dispatchCommand(PremiumShop.plugin.getServer().getConsoleSender(), cmd);
        } else {
            p.sendMessage(Ultilities.formater("&cVoce nao possui &6&lCOINS &co suficiente"));
            p.sendMessage(Ultilities.formater("&9Voce pode obter mias aqui &ahttps://loja.fallcraft.com.br"));
        }


    }


}
