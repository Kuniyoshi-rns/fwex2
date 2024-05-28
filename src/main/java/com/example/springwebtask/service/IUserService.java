package com.example.springwebtask.service;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.entity.User;
import com.example.springwebtask.form.LoginForm;

import java.util.List;

public interface IUserService {
    User findByLoginForm(LoginForm loginForm);
}
