package com.devtest.demo.service;


import com.devtest.demo.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "ProdutsSimilarService", url = "http://localhost:3001/")
public interface SimilarProductsConsumer {
    /*Realizamos la conexion con los JSON
     *
    *Consultamos los endpoints de la documentacion para traer los datos*/

    @GetMapping("/product/{productId}")
    Product getProduct(@PathVariable String productId);

    @GetMapping("/product/{productId}/similarids")
    List<String> getSimilarProduct(@PathVariable String productId);

}
