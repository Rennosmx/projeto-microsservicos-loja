package com.projeto.java.microsservicos.productapi.converter;

import com.projeto.java.microsservicos.productapi.model.Category;
import com.projeto.java.microsservicos.productapi.model.Product;
import com.projeto.java.microsservicos.shoppingclient.dto.CategoryDTO;
import com.projeto.java.microsservicos.shoppingclient.dto.ProductDTO;

public class DTOConverter {

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }
    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        if (product.getCategory() != null) {
            productDTO.setCategoryDTO(
                    DTOConverter.convert(product.getCategory()));
        }
        return productDTO;
    }

}
