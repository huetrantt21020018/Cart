package org.example.cart.Util;

public class ItemMapper {
    private static ItemMapper INSTANCE;
    public static ItemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemMapper();
        }
        return INSTANCE;
    }
    public ItemDTO toEntity(ItemDTO itemDTO) {
        ItemDTO item = new ItemDTO(itemDTO.getItemID(), itemDTO.getName());
        return item;
    }
    public ItemDTO toDTO(Item item) {
        ItemDTO dto = new ItemDTO(item.getItemID(), item.getName());
        return dto;
    }
}
