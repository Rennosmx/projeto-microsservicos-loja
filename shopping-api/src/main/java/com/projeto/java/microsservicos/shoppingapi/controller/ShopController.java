package com.projeto.java.microsservicos.shoppingapi.controller;

import com.projeto.java.microsservicos.shoppingapi.service.ShopService;
import com.projeto.java.microsservicos.shoppingapi.converter.ShopDTO;
import com.projeto.java.microsservicos.shoppingapi.converter.ShopReportDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops(){
        return shopService.getAll();
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShopsByUser(@PathVariable String userIdentifier){
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShopsByDate(@RequestBody ShopDTO shopDTO){
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping")
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop( @RequestHeader(name = "key", required = true) String key,
                            @Valid @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO, key);
    }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(  @RequestParam(name = "dataInicio", required=true)
                                            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
                                            @RequestParam(name = "dataFim", required=false)
                                            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim,
                                            @RequestParam(name = "valorMinimo", required=false) Float valorMinimo ) {

        return shopService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(   @RequestParam(name = "dataInicio", required=true)
                                            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
                                            @RequestParam(name = "dataFim", required=true)
                                            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim ) {

        return shopService.getReportByDate(dataInicio, dataFim);
    }

}
