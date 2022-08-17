package com.devtest.demo;

import com.devtest.demo.controller.ProductController;
import com.devtest.demo.dto.Product;
import com.devtest.demo.service.ProductsServiceImpl;
import com.devtest.demo.service.SimilarProductsConsumer;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductsServiceTest {


    @Mock
    private SimilarProductsConsumer controller;
    @InjectMocks
    private ProductsServiceImpl service;


    @Test
    public void shouldGetSimilarProductsOk() {
        Product producto1 = new Product("1", "Shoes", 20.99, true);
        Product producto2 = new Product("2", "Sunglasses", 15.99, true);
        List<String> products = List.of("1", "2");
        when(controller.getSimilarProduct("1")).thenReturn(products);
        when(controller.getProduct("1")).thenReturn(producto1);
        when(controller.getProduct("2")).thenReturn(producto2);

        List<Product> actual = service.getSimilarProductsId("1");

        assertEquals(2, actual.size());
        assertTrue(actual.contains(producto1));
        assertTrue(actual.contains(producto2));
    }



    @Test
    public void getSimilarProductsNotFound() {
        Product producto1 = new Product("1", "Shoes", 20.99, true);
        List<String> products = List.of("1", "2");
        when(controller.getSimilarProduct("1")).thenReturn(products);
        when(controller.getProduct("1")).thenReturn(producto1);
        when(controller.getProduct("2")).thenThrow(FeignException.NotFound.class);

        try {
            service.getSimilarProductsId("1");

        } catch (Exception ex) {
            assertEquals(FeignException.NotFound.class, ex.getClass());
        }
    }

    @Test
    public void getSimilarProductsThrowException() {
        Product producto1 = new Product("1", "Shoes", 20.99, true);
        List<String> products = List.of("1", "2");
        when(controller.getSimilarProduct("1")).thenReturn(products);
        when(controller.getProduct("1")).thenReturn(producto1);
        when(controller.getProduct("2")).thenThrow(FeignException.InternalServerError.class);

        try {
            service.getSimilarProductsId("1");

        } catch (Exception ex) {
            assertEquals(FeignException.InternalServerError.class, ex.getClass());
        }
    }
}
