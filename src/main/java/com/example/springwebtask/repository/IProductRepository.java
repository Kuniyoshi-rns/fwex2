package com.example.springwebtask.repository;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.form.NewProductForm;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    List<Product> findByName(String find);

    Product findById(int id);

    void insert(NewProductForm productForm);

    void update(NewProductForm productForm,int id);

    void delete(int id);
}
