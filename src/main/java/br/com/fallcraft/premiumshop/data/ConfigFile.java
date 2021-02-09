package br.com.fallcraft.premiumshop.data;

import br.com.fallcraft.premiumshop.core.PremiumShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigFile {
    public static PremiumShop plugin;
    private static File file;
    private static FileConfiguration userfile;

    public ConfigFile(PremiumShop main) {
        ConfigFile.plugin = main;
    }

    public static void setupConfigFile(final PremiumShop main) {
        ConfigFile.plugin = main;

        if (!ConfigFile.plugin.getDataFolder().exists()) {
            ConfigFile.plugin.getDataFolder().mkdir();
        }

        ConfigFile.file = new File(ConfigFile.plugin.getDataFolder(), "config.yml");

        if (!ConfigFile.file.exists()) {
            try {
                ConfigFile.plugin.saveResource("config.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo config.yml!");
                localException1.printStackTrace();
            }
        }
        ConfigFile.userfile = YamlConfiguration.loadConfiguration(ConfigFile.file);
    }

    public static void reload() {
        ConfigFile.userfile = YamlConfiguration.loadConfiguration(ConfigFile.file);
    }

    public static FileConfiguration getConfigFile() {
        return ConfigFile.userfile;
    }

    public static void save() {
        try {
            ConfigFile.userfile.save(ConfigFile.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}