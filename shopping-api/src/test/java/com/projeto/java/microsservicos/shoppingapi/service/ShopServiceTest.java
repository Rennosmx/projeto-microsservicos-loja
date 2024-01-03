package com.projeto.java.microsservicos.shoppingapi.service;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.projeto.java.microsservicos.shoppingapi.converter.ItemDTO;
import com.projeto.java.microsservicos.shoppingapi.converter.ShopDTO;
import com.projeto.java.microsservicos.shoppingapi.model.Shop;
import com.projeto.java.microsservicos.shoppingapi.repository.ShopRepository;
import com.projeto.java.microsservicos.shoppingclient.dto.ProductDTO;
import com.projeto.java.microsservicos.shoppingclient.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

    @InjectMocks
    private ShopService shopService;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private ShopRepository shopRepository;

    @Test
    public void test_saveShop() {

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier("123");
        itemDTO.setPrice(100F);

        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier("123");
        shopDTO.setItems(new ArrayList<>());
        shopDTO.getItems().add(itemDTO);
        shopDTO.setTotal(100F);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier("123");
        productDTO.setPreco(100F);

        Mockito.when(userService.getUserByCpf("123", "123")).thenReturn(new UserDTO());
        Mockito.when(productService.getProductByIdentifier("123")).thenReturn(productDTO);
        Mockito.when(shopRepository.save(Mockito.any())).thenReturn(Shop.convert(shopDTO));

        shopDTO = shopService.save(shopDTO, "123");

        Assertions.assertEquals(100F, shopDTO.getTotal());
        Assertions.assertEquals(1, shopDTO.getItems().size());

        Mockito.verify(shopRepository, Mockito.times(1)).save(Mockito.any());
    }


}
