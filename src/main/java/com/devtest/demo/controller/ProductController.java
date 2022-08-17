package com.devtest.demo.controller;


import com.devtest.demo.dto.Product;
import com.devtest.demo.service.ProductsServiceImpl;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductsServiceImpl service;

    public ProductController(ProductsServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(value={"/product/{productId}"}, method= RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable ("productId") String id)  throws FeignException.FeignClientException {
        try{
            return new ResponseEntity<Product>(service.getProduct(id), HttpStatus.OK);
        }
        catch (FeignException ex){
            throw ex;
        }
    }

    @RequestMapping(value={"/product/{productId}/similar"}, method= RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductSimilar(@PathVariable ("productId") String id) throws FeignException {

       try {
           return new ResponseEntity<>(service.getSimilarProductsId(id), HttpStatus.OK);
       } catch(FeignException ex){
           throw ex;
       }
    }


}
