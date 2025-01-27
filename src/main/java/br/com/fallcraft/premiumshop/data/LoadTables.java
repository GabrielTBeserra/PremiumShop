package br.com.fallcraft.premiumshop.data;

import java.sql.SQLException;
import java.sql.Statement;

public class LoadTables {
    public LoadTables() {
        try {
            load();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void load() throws SQLException, ClassNotFoundException {
        String coinBase = "create table if not exists premiumshop(\n" +
                "price double not null,\n" +
                "id integer primary key auto_increment,\n" +
                "amount integer not null,\n" +
                "block varchar(50) not null,\n" +
                "title varchar(50) not null,\n" +
                "command varchar(200) not null,\n" +
                "block_id varchar(10) not null)";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.connect();
        Statement statement = connectionFactory.getConnection().createStatement();
        statement.execute(coinBase);
        connectionFactory.disconnect();
    }
}
