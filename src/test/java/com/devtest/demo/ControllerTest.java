package com.devtest.demo;

import com.devtest.demo.controller.ProductController;
import com.devtest.demo.dto.Product;
import com.devtest.demo.service.ProductsService;
import com.devtest.demo.service.ProductsServiceImpl;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ControllerTest {

    @Mock
    private ProductsServiceImpl service;

    @InjectMocks
    private ProductController controller;

    @Test
    public void getSimilarProducts(){
        Product producto1 = new Product("1","Shoes",20.99,true);
        Product producto2 = new Product("2","Sunglasses",15.99,true);

        List<Product> products = List.of(producto1,producto2);

        when(service.getSimilarProductsId("1")).thenReturn(products);

        ResponseEntity<List<Product>> pActual = controller.getProductSimilar("1");

        assertEquals(HttpStatus.OK, pActual.getStatusCode());
        assertEquals(products, pActual.getBody());
    }
    @Test
    public void getSimilarProductsNotFound(){
        when(service.getSimilarProductsId("1")).thenThrow(FeignException.class);

        try{
            controller.getProductSimilar("1");
            fail();
        }catch (Exception ex){
            assertEquals(FeignException.class, ex.getClass());
        }
    }
    @Test
    public void getSimilarProductsThrowException(){
        when(service.getSimilarProductsId("1")).thenThrow(FeignException.InternalServerError.class);

        try{
            controller.getProductSimilar("1");
            fail();
        }catch (Exception ex){
            assertEquals(FeignException.InternalServerError.class, ex.getClass());
        }
    }
}
