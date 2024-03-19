package org.example.cart.Util;

public class Item {
    int itemID;
    String name;

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                '}';
    }

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

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
