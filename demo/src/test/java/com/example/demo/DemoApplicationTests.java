package com.example.demo;

import com.example.demo.entities.Person;
import com.example.demo.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonMapper personMapper;

    @Test
    void contextLoads() {
//        String username = "admin";
//        String password = "admin";
//        String roles = "ROLE_ROOT,ROLE_ADMIN,ROLE_USER";
//        String encoderPassword = passwordEncoder.encode(password);
//        personMapper.insertUser(new Person(1, username, encoderPassword, roles));
    }
}
