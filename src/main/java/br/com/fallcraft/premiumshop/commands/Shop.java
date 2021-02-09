package br.com.fallcraft.premiumshop.commands;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.entity.menus.ShopMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop implements CommandExecutor {
    public PremiumShop premiumShop;

    public Shop(PremiumShop premiumShop) {
        this.premiumShop = premiumShop;
        this.premiumShop.getCommand("shop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player player = (Player) sender;

        ShopMenu asd = new ShopMenu(player);
        asd.open();


        return true;
    }
}
