package com.example.demo.service;

import com.example.demo.entities.Person;
import com.example.demo.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personMapper.findByUserName(username);
        if (person == null) {
            throw new UsernameNotFoundException("未找到用户名");
        }
//        System.out.println(person.toString());
        return person;
    }
}
