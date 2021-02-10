package br.com.fallcraft.premiumshop.commands;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.data.PluginData;
import br.com.fallcraft.premiumshop.entity.Item;
import br.com.fallcraft.premiumshop.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateItem implements CommandExecutor {
    private final PremiumShop premiumShop;

    public CreateItem(PremiumShop premiumShop) {
        this.premiumShop = premiumShop;
        this.premiumShop.getCommand("createitem").setExecutor(this);
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



        String itemName = String.join(" ", args);

        PluginData.itemOpenning.put(player, new Item(itemName));

        sender.sendMessage(Ultilities.formater("&aItem criado com sucesso! Continue editando para finalizar a edicao!"));


        return true;
    }
}
