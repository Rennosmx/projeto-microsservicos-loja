package com.projeto.java.microsservicos.productapi.repository;

import com.projeto.java.microsservicos.productapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
