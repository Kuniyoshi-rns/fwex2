package com.example.springwebtask.repository;

import com.example.springwebtask.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements IProductRepository{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select products.id,product_id,categories.name category,products.name,price,image_path,description,products.created_at,products.updated_at from products join  categories on category_id=categories.id order by id",new DataClassRowMapper<>(Product.class));
    }

    @Override
    public List<Product> findByName(String find) {
        var param = new MapSqlParameterSource();
        param.addValue("find",find);
        param.addValue("likeFind","%"+find+"%");
        return jdbcTemplate.query("select products.id,product_id,categories.name category,products.name,price,image_path,description,products.created_at,products.updated_at from products join  categories on category_id=categories.id WHERE products.name LIKE :likeFind ORDER BY products.name = :find DESC, id",param,new DataClassRowMapper<>(Product.class));
    }
}
