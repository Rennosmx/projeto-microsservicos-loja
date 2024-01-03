package com.projeto.java.microsservicos.shoppingapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.java.microsservicos.shoppingclient.dto.ProductDTO;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    public static MockWebServer mockBackEnd;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() throws IOException {

        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        ReflectionTestUtils.setField(productService, "productApiURL", baseUrl);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void test_getProductByIdentifier() throws Exception {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setPreco(1000F);
        productDTO.setProductIdentifier("prod-identifier");

        ObjectMapper objectMapper = new ObjectMapper();
        mockBackEnd.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(productDTO))
                                              .addHeader("Content-Type", "application/json"));

        productDTO = productService.getProductByIdentifier("prod-identifier");

        Assertions.assertEquals(1000F, productDTO.getPreco());
        Assertions.assertEquals("prod-identifier", productDTO.getProductIdentifier());
    }

}
