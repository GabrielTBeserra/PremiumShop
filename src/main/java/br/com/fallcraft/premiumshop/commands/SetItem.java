package br.com.fallcraft.premiumshop.commands;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.data.PluginData;
import br.com.fallcraft.premiumshop.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetItem implements CommandExecutor {
    private final PremiumShop premiumShop;

    public SetItem(PremiumShop premiumShop) {
        this.premiumShop = premiumShop;
        this.premiumShop.getCommand("setitem").setExecutor(this);
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



        String blockId = player.getInventory().getItemInHand().getType().toString();
        String type = player.getInventory().getItemInHand().getDurability() + "";


        PluginData.itemOpenning.get(player).setBlockId(blockId);
        PluginData.itemOpenning.get(player).setType(type);

        sender.sendMessage(Ultilities.formater("&aItem definido com sucesso!"));

        return true;
    }

}
