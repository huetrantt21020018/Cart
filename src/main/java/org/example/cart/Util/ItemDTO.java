package org.example.cart.Util;

import static java.lang.Math.max;

public class ItemDTO {
    private static int count = 0;
    private int itemID;
    private String name;

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                '}';
    }

    public ItemDTO(int itemID, String name) {
        this.itemID = itemID;
        this.name = name;
        if(itemID == 0) this.itemID = count + 1;
        count = max(count, itemID);
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
