package org.example.cart.Util;

public class Order {
    int itemID;
    String itemName;
    int count;

    public Order(int itemID, String itemName, int count) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.count = count;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCount() {
        return count;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
