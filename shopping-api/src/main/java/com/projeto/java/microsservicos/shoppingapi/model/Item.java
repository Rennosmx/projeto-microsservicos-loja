package com.projeto.java.microsservicos.shoppingapi.model;

import com.projeto.java.microsservicos.shoppingapi.converter.ItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {

    private String productIdentifier;
    private Float price;

    public static Item convert(ItemDTO itemDTO) {

        Item item = new Item();

        item.setProductIdentifier(itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());

        return item;
    }
}
