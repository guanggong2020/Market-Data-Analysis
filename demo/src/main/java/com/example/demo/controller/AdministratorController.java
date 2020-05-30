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
public class AdministratorController {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/administrators")
    public String list(Model model) {
        List<Person> administrators = new ArrayList<>();
        for (Person administrator : personMapper.getUser()) {
            String[] roles = administrator.getRoles().split(",");
            if (roles[0].equals("ROLE_ADMIN")) {
                administrator.setPassword("******");
                administrators.add(administrator);
            }
        }
        model.addAttribute("administrators", administrators);
        return "administrator/list";
    }

    @GetMapping("/administrator/add")
    public String toAddPage(Model model){
        return "administrator/add";
    }

    @PostMapping("/administrator/add")
    public String addUser(Person administrator, Model model) {
        if (!administrator.getUsername().trim().equals("") && !administrator.getPassword().trim().equals("")) {
            if (personMapper.findByUserName(administrator.getUsername()) != null) {
                model.addAttribute("msg", "该用户名已存在");
                return "administrator/add";
            }
            administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
            personMapper.insertUser(administrator);
        }
        return "redirect:/administrators";
    }

    @GetMapping("/administrator/edit/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Person administrator = personMapper.getUserById(id);
        administrator.setPassword("请重新填写");
        model.addAttribute("administrator", administrator);
        return "administrator/add";
    }

    @DeleteMapping("/administrator/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        personMapper.deleteUserById(id);
        return "redirect:/administrators";
    }

    @PutMapping("/administrator/add")
    public String updateUser(Person administrator) {
        if (!administrator.getUsername().trim().equals("") && !administrator.getPassword().trim().equals("")) {
            administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
            personMapper.updateUser(administrator);
        }
        return "redirect:/administrators";
    }
}
