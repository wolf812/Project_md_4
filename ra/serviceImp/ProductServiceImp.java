package com.ra.serviceImp;

import com.ra.model.Product;
import com.ra.repository.ProductRepository;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> displayData(String productName, int page, int size, String direction, String sortBy) {
        //Khởi tạo đối tượng Pageable
        Pageable pageable = PageRequest.of(page, size,
                direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        List<Product> listProduct = productRepository.findByProductName(productName, pageable).getContent();
        return listProduct;
    }

    @Override
    public List<Integer> getListPage(String productName, int size) {
        int countProduct = productRepository.countByProductNameContains(productName);
        List<Integer> listPage = new ArrayList<>();
        for (int i = 0; i < (int) Math.ceil((double) countProduct / (double) size); i++) {
            listPage.add(i + 1);
        }
        return listPage;
    }

    @Override
    public Product findById(String productId) {
        try {
            Product productById = productRepository.findById(productId).get();
            return productById;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Product save(Product product) {
        try {
            Product proNew = productRepository.save(product);
            return proNew;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String productId) {
        try {
            productRepository.deleteById(productId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public int productStatusTrue() {
        return 0;
    }

    @Override
    public int productStatusFalse() {
        return 0;
    }

    @Override
    public boolean productStatus(String productId) {
        return false;
    }
}
