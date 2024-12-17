package com.jkbd.api.api.service.impl;

import com.jkbd.api.api.dto.UsersDTO;
import com.jkbd.api.api.entity.Users;
import com.jkbd.api.api.repositories.UserRepository;
import com.jkbd.api.api.service.UserService;
import com.jkbd.api.api.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public Users create(UsersDTO obj) {
        return repository.save(mapper.map(obj, Users.class));
    }
}
