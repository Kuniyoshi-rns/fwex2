package com.example.springwebtask.service;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String find) {
        return productRepository.findByName(find);
    }
}
