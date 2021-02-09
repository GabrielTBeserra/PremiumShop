package br.com.fallcraft.premiumshop.commands;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.data.PluginData;
import br.com.fallcraft.premiumshop.data.ShopData;
import br.com.fallcraft.premiumshop.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FinishItem implements CommandExecutor {
    private final PremiumShop premiumShop;

    public FinishItem(PremiumShop premiumShop) {
        this.premiumShop = premiumShop;
        this.premiumShop.getCommand("finishitem").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command only from players");
            return true;
        }

        Player player = (Player) sender;


        if (!PluginData.itemOpenning.containsKey(player)) {
            sender.sendMessage(Ultilities.formater("&cVoce nao tem nenhum item para editar, use &1/createitem &6<NOME>"));
            return true;
        }


        if (!PluginData.itemOpenning.get(player).isFinish()) {
            sender.sendMessage(Ultilities.formater("&cVoce nao pode finalizar um item que nao esta completo"));
            PluginData.itemOpenning.get(player).print(sender);
            return true;
        }


        sender.sendMessage(Ultilities.formater("&aItem criado com sucesso!"));
        new ShopData().addItem(PluginData.itemOpenning.get(player));
        PluginData.itemOpenning.remove(player);

        return true;
    }

}
