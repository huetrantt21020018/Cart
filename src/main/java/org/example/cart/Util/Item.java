package org.example.cart.Util;

public class Item {
    private int itemID;
    private String name;

    public Item(int itemID, String name) {
        this.itemID = itemID;
        this.name = name;
    }

    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }
}
