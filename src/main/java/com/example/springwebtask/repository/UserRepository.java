package com.example.springwebtask.repository;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.entity.User;
import com.example.springwebtask.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public User findByLoginForm(LoginForm loginForm) {
        var param = new MapSqlParameterSource();
        param.addValue("login_id",loginForm.getLoginId());
        param.addValue("pass",loginForm.getPassword());
        var list = jdbcTemplate.query("select * from users where login_id = :login_id and password = :pass",param,new DataClassRowMapper<>(User.class));
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

}
