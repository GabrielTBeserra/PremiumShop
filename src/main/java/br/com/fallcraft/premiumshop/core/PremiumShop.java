package br.com.fallcraft.premiumshop.core;

import br.com.fallcraft.premiumshop.commands.*;
import br.com.fallcraft.premiumshop.data.ConfigFile;
import br.com.fallcraft.premiumshop.data.ConnectionFactory;
import br.com.fallcraft.premiumshop.data.LoadTables;
import br.com.fallcraft.premiumshop.data.PluginData;
import br.com.fallcraft.premiumshop.listener.MenuListener;
import br.com.fallcraft.premiumshop.listener.RemoveFromMap;
import br.com.fallcraft.premiumshop.utils.Ultilities;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class PremiumShop extends JavaPlugin {
    public static PremiumShop plugin;

    @Override
    public void onEnable() {
        plugin = this;
        PluginData.itemOpenning = new HashMap<>();


        ConfigFile.setupConfigFile(this);

        if (loadDataBase()) {
            getServer().getConsoleSender().sendMessage(Ultilities.formater("&aLoginFallCraft carregado com sucesso!"));
            getServer().getConsoleSender().sendMessage(Ultilities.formater("&3LoginFallCraft: Criado por " + getDescription().getAuthors().get(0)));
            //  loadEvents();
            //   loadCommands();

        } else {
            this.onDisable();
        }

        load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean loadDataBase() {
        String database = ConfigFile.getConfigFile().getString("database.type").toLowerCase();

        if (!(database.equals("mysql") || database.equals("sqlite"))) {
            getServer().getConsoleSender().sendMessage(Ultilities.formater("&cErro ao conectar com o banco de dados, verifique o arquivo config.cfg"));
            getServer().getConsoleSender().sendMessage(Ultilities.formater("&cDesabilitando plugin!"));
            return false;
        }

        ConnectionFactory.databaseType = database;
        new LoadTables();
        return true;
    }

    private void load() {
        new CreateItem(this);
        new FinishItem(this);
        new SetAmount(this);
        new SetCommand(this);
        new SetItem(this);
        new SetPrice(this);
        new Shop(this);
        new MenuListener(this);
        new RemoveFromMap(this);
        new CancelItem(this);
        new DeleteItem(this);
    }
}
