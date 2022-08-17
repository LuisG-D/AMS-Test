package com.devtest.demo.service;


import com.devtest.demo.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class ProductsServiceImpl implements ProductsService{

       SimilarProductsConsumer consumer;

    public ProductsServiceImpl(SimilarProductsConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public Product getProduct(String id) {
        return this.consumer.getProduct(id);
    }

    @Override
    public List<Product> getSimilarProductsId(String id) {
        return this.consumer.getSimilarProduct(id)
                .stream()
                .map(consumer::getProduct)
                .collect(Collectors.toList());
    }
}
