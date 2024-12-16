package com.jkbd.api.api.service;

import com.jkbd.api.api.entity.Users;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users> findAll();
}
