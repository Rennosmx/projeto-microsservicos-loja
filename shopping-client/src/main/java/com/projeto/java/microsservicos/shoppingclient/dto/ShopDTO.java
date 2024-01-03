package com.projeto.java.microsservicos.shoppingclient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    @NotBlank
    private String userIdentifier;
    private Float total;
    private LocalDateTime date;
    @NotNull
    private List<ItemDTO> items;


}
