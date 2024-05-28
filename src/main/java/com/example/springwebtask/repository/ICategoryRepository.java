package com.example.springwebtask.repository;

import com.example.springwebtask.entity.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> findAll();
}
