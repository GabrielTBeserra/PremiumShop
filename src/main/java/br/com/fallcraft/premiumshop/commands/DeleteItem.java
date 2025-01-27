package br.com.fallcraft.premiumshop.commands;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.data.PluginData;
import br.com.fallcraft.premiumshop.data.ShopData;
import br.com.fallcraft.premiumshop.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteItem implements CommandExecutor {
    private final PremiumShop premiumShop;

    public DeleteItem(PremiumShop premiumShop) {
        this.premiumShop = premiumShop;
        this.premiumShop.getCommand("deleteitem").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command only from players");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage(Ultilities.formater("&aUse /createitem &6<NOME>"));
            return true;
        }

        if (PluginData.itemOpenning.containsKey(player)) {
            sender.sendMessage(Ultilities.formater("&cVoce ja esta criando um item, use &e/cancelitem &cpara cancelar"));
            return true;
        }


        int id = Integer.parseInt(args[0]);

        ShopData shopData = new ShopData();
        shopData.deleteItem(id);

        sender.sendMessage(Ultilities.formater("&aItem apagado com sucesso!"));


        return true;
    }
}
