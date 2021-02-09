package br.com.fallcraft.premiumshop.entity;

import br.com.fallcraft.premiumshop.utils.Ultilities;
import org.bukkit.command.CommandSender;

public class Item {
    private int id;
    private int amount;
    private String title;
    private String commnad;
    private String blockId;
    private String type;
    private double price;


    public Item() {

    }

    public Item(String title) {
        this.title = title;
    }

    public Item(int id, int amount, String title, String commnad, String blockId, String type, double price) {
        this.id = id;
        this.amount = amount;
        this.title = title;
        this.commnad = commnad;
        this.blockId = blockId;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommnad() {
        return commnad;
    }

    public void setCommnad(String commnad) {
        this.commnad = commnad;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public boolean isFinish() {
        return amount != 0 && title != null && commnad != null && blockId != null && price > 0;

    }

    public void print(CommandSender sender) {
        String sAmount = amount != 0 ? "&a" : "&c" + "amount";
        String sCommand = commnad != null ? "&a" : "&c" + "command";
        String sBlockId = blockId != null ? "&a" : "&c" + "item";
        String sPrice = price > 0 ? "&a" : "&c" + "price";

        sender.sendMessage(Ultilities.formater(sAmount));
        sender.sendMessage(Ultilities.formater(sCommand));
        sender.sendMessage(Ultilities.formater(sBlockId));
        sender.sendMessage(Ultilities.formater(sPrice));


    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", amount=" + amount +
                ", title='" + title + '\'' +
                ", commnad='" + commnad + '\'' +
                ", blockId='" + blockId + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
