package br.com.fallcraft.premiumshop.data;


import br.com.fallcraft.premiumshop.core.PremiumShop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static String databaseType;
    private static Connection connection = null;
    private final PremiumShop pl = PremiumShop.plugin;

    private ConnectionFactory() throws ClassNotFoundException, SQLException {
        if (databaseType.equals("sqlite")) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + pl.getDataFolder() + "/premiumshop.db");
        } else if (databaseType.equals("mysql")) {
            final String host = ConfigFile.getConfigFile().getString("database.host");
            final String database = ConfigFile.getConfigFile().getString("database.database");
            final String URL = "jdbc:mysql://" + host + "/" + database + "?autoReconnect=true";
            final String driver = "com.mysql.jdbc.Driver";
            final String user = ConfigFile.getConfigFile().getString("database.user");
            final String password = ConfigFile.getConfigFile().getString("database.password");
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, user, password);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null) {
                new ConnectionFactory();
            } else {
                System.out.println(connection.isClosed() + "");
                if (connection.isClosed()) {
                    new ConnectionFactory();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return connection;
    }
}
