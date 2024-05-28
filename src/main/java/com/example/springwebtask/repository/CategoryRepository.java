package com.example.springwebtask.repository;

import com.example.springwebtask.entity.Category;
import com.example.springwebtask.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class CategoryRepository implements ICategoryRepository{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("select * from categories order by id",new DataClassRowMapper<>(Category.class));
    }
}
