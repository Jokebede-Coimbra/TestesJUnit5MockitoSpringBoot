package com.jkbd.api.api.config;

import com.jkbd.api.api.entity.Users;
import com.jkbd.api.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void starDB() {
        Users u1 = new Users(null, "Jayanne", "jay@mail.com", "123");
        Users u2 = new Users(null, "Samarah", "sam@mail.com", "123");
        repository.saveAll(List.of(u1, u2));
    }
}
