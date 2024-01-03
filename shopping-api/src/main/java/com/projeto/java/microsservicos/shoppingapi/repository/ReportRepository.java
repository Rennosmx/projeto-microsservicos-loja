package com.projeto.java.microsservicos.shoppingapi.repository;

import com.projeto.java.microsservicos.shoppingapi.model.Shop;
import com.projeto.java.microsservicos.shoppingapi.converter.ShopReportDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {

    public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);
    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim);

}
