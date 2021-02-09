package br.com.fallcraft.premiumshop.data;

import br.com.fallcraft.premiumshop.entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopData {
    public void addItem(Item item) {

        try {
            String newPlayer = "insert into premiumshop (price, amount , block , title , command , block_id ) values (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(newPlayer);
            preparedStatement.setDouble(1, item.getPrice());
            preparedStatement.setDouble(2, item.getAmount());
            preparedStatement.setString(3, item.getBlockId());
            preparedStatement.setString(4, item.getTitle());
            preparedStatement.setString(5, item.getCommnad());
            preparedStatement.setString(6, item.getType());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItens() {
        List<Item> itens = new ArrayList<>();

        try {
            String query = "select * from premiumshop";
            PreparedStatement sets = ConnectionFactory.getConnection().prepareStatement(query);
            ResultSet results = sets.executeQuery();

            while (results.next()) {
                itens.add(
                        new Item(results.getInt("id")
                                , results.getInt("amount")
                                , results.getString("title")
                                , results.getString("command")
                                , results.getString("block")
                                , results.getString("block_id")
                                , results.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return itens;
    }

    public Item getItem(int id) {
        Item item = null;

        try {
            String query = "select * from premiumshop where id = ?";


            PreparedStatement sets = ConnectionFactory.getConnection().prepareStatement(query);
            sets.setInt(1, id);
            ResultSet results = sets.executeQuery();

            while (results.next()) {
                item =
                        new Item(results.getInt("id")
                                , results.getInt("amount")
                                , results.getString("title")
                                , results.getString("command")
                                , results.getString("block")
                                , results.getString("block_id")
                                , results.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return item;
    }
}
