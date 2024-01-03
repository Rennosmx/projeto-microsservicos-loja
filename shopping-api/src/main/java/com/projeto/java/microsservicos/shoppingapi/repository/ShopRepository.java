package com.projeto.java.microsservicos.shoppingapi.repository;

import com.projeto.java.microsservicos.shoppingapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShopRepository extends  ReportRepository, JpaRepository<Shop, Long> {

    public List<Shop> findAllByUserIdentifier(String userIdentifier);
    public List<Shop> findAllByTotalGreaterThan(Float total);
    List<Shop> findAllByDateGreaterThan(LocalDateTime date);



}
