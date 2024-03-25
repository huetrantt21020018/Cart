package org.example.cart.Util;

public class Order {
    private int itemID;
    private int count;

    public Order(int itemID, int count) {
        this.itemID = itemID;
        this.count = count;
    }

    public int getItemID() {
        return itemID;
    }

    public int getCount() {
        return count;
    }
}
