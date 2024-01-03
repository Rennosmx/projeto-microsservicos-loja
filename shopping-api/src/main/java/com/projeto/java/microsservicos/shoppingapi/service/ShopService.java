package com.projeto.java.microsservicos.shoppingapi.service;

import com.projeto.java.microsservicos.shoppingapi.converter.DTOConverter;
import com.projeto.java.microsservicos.shoppingapi.converter.ItemDTO;
import com.projeto.java.microsservicos.shoppingapi.converter.ShopDTO;
import com.projeto.java.microsservicos.shoppingapi.converter.ShopReportDTO;
import com.projeto.java.microsservicos.shoppingapi.model.Shop;
import com.projeto.java.microsservicos.shoppingapi.repository.ShopRepository;
import com.projeto.java.microsservicos.shoppingclient.dto.ProductDTO;
import com.projeto.java.microsservicos.shoppingclient.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<ShopDTO> getAll(){

        List<Shop> shops = new ArrayList<Shop>();

        return shopRepository.findAll()
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){

        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);

        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){

        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());

        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long productId){
        Optional<Shop> shop = shopRepository.findById(productId);

        if(shop.isPresent()){
            return ShopDTO.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {

        UserDTO userDTO = userService.getUserByCpf(shopDTO.getUserIdentifier(), key);
        validateProducts(shopDTO.getItems());

        /*
        if (userService.getUserByCpf(shopDTO.getUserIdentifier(), key) == null) {
            return null;
        }

        if (!validateProducts(shopDTO.getItems())) {
            return null;
        }
        */

        shopDTO.setTotal(shopDTO.getItems()
                                .stream()
                                .map(x -> x.getPrice())
                                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());
        shop = shopRepository.save(shop);

        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {

        for (ItemDTO item : items) {

            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());

            if (productDTO == null) {
                return false;
            }

            item.setPrice(productDTO.getPreco());
        }

        return true;
    }

    public List<ShopDTO> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {

        List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }

}
