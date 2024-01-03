package com.projeto.java.microsservicos.shoppingapi.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopReportDTO {

    private Integer count;
    private Double total;
    private Double mean;

}
