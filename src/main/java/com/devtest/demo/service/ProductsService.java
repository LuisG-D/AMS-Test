package com.devtest.demo.service;


import com.devtest.demo.dto.Product;
import java.util.List;


public interface ProductsService {

    public Product getProduct(String id);

    public List<Product> getSimilarProductsId(String id);

}
