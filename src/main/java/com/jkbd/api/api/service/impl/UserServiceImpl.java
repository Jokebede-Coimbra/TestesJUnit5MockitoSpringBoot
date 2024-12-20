package com.jkbd.api.api.service.impl;

import com.jkbd.api.api.dto.UsersDTO;
import com.jkbd.api.api.entity.Users;
import com.jkbd.api.api.repositories.UserRepository;
import com.jkbd.api.api.service.UserService;
import com.jkbd.api.api.service.exceptions.DataIntegrityViolationException;
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
    public Users create(UsersDTO usersDTO) {
        findByEmail(usersDTO);
        return repository.save(mapper.map(usersDTO, Users.class));
    }

    @Override
    public Users update(UsersDTO usersDTO) {
        findByEmail(usersDTO);
        return repository.save(mapper.map(usersDTO, Users.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UsersDTO usersDTO) {
        Optional<Users> users = repository.findByEmail(usersDTO.getEmail());
        if (users.isPresent() && users.get().getId().equals(usersDTO.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }
}
