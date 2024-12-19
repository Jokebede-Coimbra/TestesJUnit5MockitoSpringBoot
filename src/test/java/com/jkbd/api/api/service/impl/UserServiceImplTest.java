package com.jkbd.api.api.service.impl;

import com.jkbd.api.api.dto.UsersDTO;
import com.jkbd.api.api.entity.Users;
import com.jkbd.api.api.repositories.UserRepository;
import com.jkbd.api.api.service.exceptions.DataIntegratyViolationException;
import com.jkbd.api.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Jayanne";
    public static final String EMAIL = "jay@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private Users users;
    private UsersDTO usersDTO;
    private Optional<Users> optionalUsers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnAnObjectFound() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUsers);

        Users userResponse = userService.findById(ID);

        assertNotNull(userResponse);
        assertEquals(Users.class, userResponse.getClass());
        assertEquals(ID, userResponse.getId());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            userService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(users));

        List<Users> response = userService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Users.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(users);

        Users usersResponse = userService.create(usersDTO);

        assertNotNull(usersResponse);
        assertEquals(Users.class, usersResponse.getClass());
        assertEquals(ID, usersResponse.getId());
        assertEquals(NAME, usersResponse.getName());
        assertEquals(EMAIL, usersResponse.getEmail());
        assertEquals(PASSWORD, usersResponse.getPassword());

    }
    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUsers);

        try {
            optionalUsers.get().setId(2);
            userService.create(usersDTO);
        } catch(Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(users);

        Users usersResponse = userService.update(usersDTO);

        assertNotNull(usersResponse);
        assertEquals(Users.class, usersResponse.getClass());
        assertEquals(ID, usersResponse.getId());
        assertEquals(NAME, usersResponse.getName());
        assertEquals(EMAIL, usersResponse.getEmail());
        assertEquals(PASSWORD, usersResponse.getPassword());

    }

    @Test
    void delete() {
    }

    private void startUsers() {
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
    }
}