package com.example.springwebtask.entity;

public record User(int id,String login_id, String password ,String name, int role, String created_at, String updated_at){}
