package com.data.sesson15_webjava.service;

import com.data.sesson15_webjava.model.Product2;
import com.data.sesson15_webjava.repository.Product2Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Product2Service {
    private Product2Repository repo = new Product2Repository();

    public List<Product2> getAllProducts() {
        return repo.findAll();
    }

    public Product2 getProductById(int id) {
        return repo.findById(id);
    }
}
