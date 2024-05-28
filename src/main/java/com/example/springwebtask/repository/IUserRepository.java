package com.example.springwebtask.repository;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.entity.User;
import com.example.springwebtask.form.LoginForm;

import java.util.List;

public interface IUserRepository {
    User findByLoginForm(LoginForm loginForm);
}
