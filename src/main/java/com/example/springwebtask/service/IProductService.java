package com.example.springwebtask.service;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.form.NewProductForm;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();

    public List<Product> findByName(String find);

    public Product findById(int id);

    public void insert(NewProductForm productForm,String time);

    public void update(NewProductForm productForm, String time, int id);

    public void delete(int id);
}
