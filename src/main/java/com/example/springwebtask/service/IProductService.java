package com.example.springwebtask.service;

import com.example.springwebtask.entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();

    public List<Product> findByName(String find);
}
