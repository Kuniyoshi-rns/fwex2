package com.example.springwebtask.repository;

import com.example.springwebtask.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    List<Product> findByName(String find);
}
