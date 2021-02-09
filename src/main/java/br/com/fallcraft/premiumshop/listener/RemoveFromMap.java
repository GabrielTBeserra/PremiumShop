package br.com.fallcraft.premiumshop.listener;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import br.com.fallcraft.premiumshop.data.PluginData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class RemoveFromMap implements Listener {
    private final PremiumShop premiumShop;

    public RemoveFromMap(PremiumShop premiumShop) {
        this.premiumShop = premiumShop;
        this.premiumShop.getServer().getPluginManager().registerEvents(this, this.premiumShop);
    }

    @EventHandler
    public void removeFromMap(PlayerQuitEvent event) {
        PluginData.itemOpenning.remove(event.getPlayer());
    }


}
