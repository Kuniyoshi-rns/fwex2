package com.example.springwebtask.service;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.form.NewProductForm;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();

    public List<Product> findByName(String find);

    List<Product> findByCategory(String find);

    public Product findById(int id);

    public void insert(NewProductForm productForm);

    public void update(NewProductForm productForm,int id);

    public void delete(int id);

    public List<Product> multiFind(List<String> findList);
}
