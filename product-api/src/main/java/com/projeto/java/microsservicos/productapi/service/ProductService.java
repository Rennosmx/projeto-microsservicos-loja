package com.projeto.java.microsservicos.productapi.service;

import com.projeto.java.microsservicos.productapi.converter.DTOConverter;
import com.projeto.java.microsservicos.productapi.model.Product;
import com.projeto.java.microsservicos.productapi.repository.CategoryRepository;
import com.projeto.java.microsservicos.productapi.repository.ProductRepository;
import com.projeto.java.microsservicos.shoppingclient.dto.ProductDTO;
import com.projeto.java.microsservicos.shoppingclient.exception.CategoryNotFoundException;
import com.projeto.java.microsservicos.shoppingclient.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {

        List<Product> products = productRepository.findAll();

        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {

        List<Product> products = productRepository.getProductByCategory(categoryId);

        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {

        Product product = productRepository.findByProductIdentifier(productIdentifier);

        if (product != null) {
            return DTOConverter.convert(product);
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO) {

        Boolean existsCategory = categoryRepository.existsById(productDTO.getCategoryDTO().getId());

        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }
        Product product = productRepository.save(Product.convert(productDTO));
        return DTOConverter.convert(product);
    }

    public ProductDTO delete(long ProductId) throws ProductNotFoundException {

        Optional<Product> Product = productRepository.findById(ProductId);

        if (Product.isPresent()) {
            productRepository.delete(Product.get());
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO editProduct(long id, ProductDTO dto) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        if (dto.getNome() != null ) {
            product.setNome(dto.getNome());
        }
        if (dto.getPreco() != null) {
            product.setPreco(dto.getPreco());
        }

        return DTOConverter.convert(productRepository.save(product));
    }

    public Page<ProductDTO> getAllPage(Pageable page) {

        Page<Product> products = productRepository.findAll(page);

        return products
                .map(DTOConverter::convert);
    }

}
