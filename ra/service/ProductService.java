package com.ra.service;

import com.ra.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> displayData(String productName, int page, int size, String direction, String sortBy);

    List<Integer> getListPage(String productName, int size);

    Product findById(String productId);

    Product save(Product product);

    boolean update(Product product);

    boolean delete(String productId);
    List<Product> findAll();

    int productStatusTrue();
    int productStatusFalse();
    boolean productStatus(String productId);
}
