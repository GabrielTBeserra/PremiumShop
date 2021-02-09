package br.com.fallcraft.premiumshop.data;

import br.com.fallcraft.premiumshop.entity.Item;
import org.bukkit.entity.Player;

import java.util.Map;

public class PluginData {
    /*
     * Registra quem esta criando um novo item
     * para que nao de erro na criacao
     */
    public static Map<Player, Item> itemOpenning;
}
