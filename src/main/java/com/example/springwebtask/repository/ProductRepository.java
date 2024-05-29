package com.example.springwebtask.repository;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.form.NewProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    @Override
    public Product findById(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        var list = jdbcTemplate.query("select products.id,product_id,categories.name category,products.name,price,image_path,description,products.created_at,products.updated_at from products join  categories on category_id=categories.id WHERE products.id = :id",param,new DataClassRowMapper<>(Product.class));
        return list.get(0);
    }

    @Override
    public void insert(NewProductForm productForm,String time) {
        var param = new MapSqlParameterSource();
        param.addValue("product_id",productForm.getProductId());
        param.addValue("name",productForm.getProductName());
        param.addValue("price",productForm.getPriceValue());
        param.addValue("category_id",productForm.getCategoryId());
        System.out.println(productForm.getCategoryId());
        param.addValue("description",productForm.getDescription());
        param.addValue("image_path",productForm.getImgPath());
        param.addValue("time", Timestamp.valueOf(time));
        jdbcTemplate.update("insert into products(product_id,category_id,name,price,image_path,description,created_at,updated_at)" +
                " values(:product_id,:category_id,:name,:price,:image_path,:description,:time,:time)",param);
    }

    @Override
    public void update(NewProductForm productForm, String time, int id) {
        var param = new MapSqlParameterSource();
        System.out.println(id);
        param.addValue("id",id);
        param.addValue("product_id",productForm.getProductId());
        param.addValue("name",productForm.getProductName());
        param.addValue("price",productForm.getPriceValue());
        param.addValue("category_id",productForm.getCategoryId());
        System.out.println(productForm.getCategoryId());
        param.addValue("description",productForm.getDescription());
        param.addValue("image_path",productForm.getImgPath());
        param.addValue("time", Timestamp.valueOf(time));
        jdbcTemplate.update("update products set product_id=:product_id,name=:name,category_id=:category_id, price=:price, description=:description, updated_at=:time where id = :id",param);
    }

    @Override
    public void delete(int id) {
        var param = new MapSqlParameterSource();
        System.out.println(id);
        param.addValue("id",id);
        jdbcTemplate.update("delete from products where id = :id",param);
    }
}
