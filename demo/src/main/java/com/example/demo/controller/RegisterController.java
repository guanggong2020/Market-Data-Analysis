package com.example.demo.controller;

import com.example.demo.entities.Person;
import com.example.demo.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonMapper personMapper;

    @GetMapping("/register")
    public String welcome() { return "register"; }

    @PostMapping("/register")
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        if (!username.trim().equals("") && !password.trim().equals("")) {
            if (personMapper.findByUserName(username) != null) {
                model.addAttribute("msg","该用户名已存在");
                return "register";
            }
            Person user = new Person(0, username, passwordEncoder.encode(password), "ROLE_USER");
            personMapper.insertUser(user);
        }
        return "login";
    }
}
