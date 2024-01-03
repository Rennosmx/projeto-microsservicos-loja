package com.projeto.java.microsservicos.shoppingapi.converter;

import com.projeto.java.microsservicos.shoppingapi.model.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;

    public static ItemDTO convert(Item item) {

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());

        return itemDTO;
    }

}
