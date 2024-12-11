package com.jkbd.api.api.service.impl;

import com.jkbd.api.api.entity.Users;
import com.jkbd.api.api.repositories.UserRepository;
import com.jkbd.api.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
