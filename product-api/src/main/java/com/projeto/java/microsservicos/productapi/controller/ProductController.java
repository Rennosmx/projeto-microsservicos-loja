package com.projeto.java.microsservicos.productapi.controller;

import com.projeto.java.microsservicos.productapi.service.ProductService;
import com.projeto.java.microsservicos.shoppingclient.dto.ProductDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId){
        return productService.getProductByCategoryId(categoryId);
    }

    @GetMapping("/{productIdentifier}")
    public ProductDTO findById(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO userDTO) {
        return productService.save(userDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO editProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.editProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<ProductDTO> getProductsPage(Pageable pageable) {
        return productService.getAllPage(pageable);
    }

}
