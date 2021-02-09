package br.com.fallcraft.premiumshop.commands;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.data.PluginData;
import br.com.fallcraft.premiumshop.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetAmount implements CommandExecutor {
    private final PremiumShop premiumShop;

    public SetAmount(PremiumShop premiumShop) {
        this.premiumShop = premiumShop;
        this.premiumShop.getCommand("setamount").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command only from players");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            return false;
        }

        if (!PluginData.itemOpenning.containsKey(player)) {
            sender.sendMessage(Ultilities.formater("&cVoce nao tem nenhum item para editar, use &1/createitem &6<NOME>"));
            return true;
        }



        int amount = Integer.parseInt(args[0]);
        PluginData.itemOpenning.get(player).setAmount(amount);

        sender.sendMessage(Ultilities.formater("&aQuantidade definida com sucesso!"));

        return true;
    }

}
