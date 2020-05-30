package com.example.demo.controller;

import com.example.demo.entities.Person;
import com.example.demo.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String list(Model model) {
        List<Person> users = new ArrayList<>();
        for (Person user : personMapper.getUser()) {
            String[] roles = user.getRoles().split(",");
            if (roles[0].equals("ROLE_USER")) {
                user.setPassword("******");
                users.add(user);
            }
        }
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/user/add")
    public String toAddPage(Model model){
        return "user/add";
    }

    @PostMapping("/user/add")
    public String addUser(Person user, Model model) {
        if (!user.getUsername().trim().equals("") && !user.getPassword().trim().equals("")) {
            if (personMapper.findByUserName(user.getUsername()) != null) {
                model.addAttribute("msg", "该用户名已存在");
                return "user/add";
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            personMapper.insertUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/user/edit/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Person user = personMapper.getUserById(id);
        user.setPassword("请重新填写");
        model.addAttribute("user", user);
        return "user/add";
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        personMapper.deleteUserById(id);
        return "redirect:/users";
    }

    @PutMapping("/user/add")
    public String updateUser(Person user) {
        if (!user.getUsername().trim().equals("") && !user.getPassword().trim().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            personMapper.updateUser(user);
        }
        return "redirect:/users";
    }
}
