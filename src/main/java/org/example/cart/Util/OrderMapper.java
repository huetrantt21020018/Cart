package org.example.cart.Util;

public class OrderMapper {
    private static OrderMapper INSTANCE;
    public static OrderMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderMapper();
        }
        return INSTANCE;
    }
    public Order toEntity(OrderDTO itemdto) {
        Order item = new Order(itemdto.getItemID(), itemdto.getCount());
        return item;
    }
    public OrderDTO toDTO(Order item, String name) {
        OrderDTO dto = new OrderDTO(item.getItemID(), name, item.getCount());
        return dto;
    }
}
