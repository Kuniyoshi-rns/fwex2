package com.example.springwebtask.service;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.entity.User;
import com.example.springwebtask.form.LoginForm;
import com.example.springwebtask.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public User findByLoginForm(LoginForm loginForm) {
        return userRepository.findByLoginForm(loginForm);
    }


}
