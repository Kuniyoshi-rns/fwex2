package com.example.springwebtask.service;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.form.NewProductForm;
import com.example.springwebtask.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<Product> findByCategory(String find) {
        return productRepository.findByCategory(find);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void insert(NewProductForm productForm) {
        productRepository.insert(productForm);
    }

    @Override
    public void update(NewProductForm productForm,int id) {
        productRepository.update(productForm,id);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> multiFind(List<String> findList){
        Set<Product> set = new HashSet<>();
        for(var find:findList){
            set.addAll(productRepository.findByName(find));
            set.addAll(productRepository.findByCategory(find));
        }
        return set.stream().toList();
    }
}
