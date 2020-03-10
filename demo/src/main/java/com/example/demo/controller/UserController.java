package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //查询所有用户返回列表页面
    @GetMapping("/users")
    public String list(Model model){
        List<User> users = userMapper.getUser();
        //放在请求域中
        model.addAttribute("users", users);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "user/list";
    }

    //来到员工添加页面
    @GetMapping("/user/add")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        return "user/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/user/add")
    public String addOrUpdateUser(User user){
        //来到员工列表页面
        System.out.println("保存的员工信息：" + user);
        //保存员工
        User u = userMapper.getUserById(user.getId());
        if (u == null)
            userMapper.insertUser(user);
        else
            userMapper.updateUser(user);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/users";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/user/edit/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        User user = userMapper.getUserById(id);
        model.addAttribute("user", user);
        return "user/add";
    }

    //员工删除
    @GetMapping("/user/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        userMapper.deleteUserById(id);
        return "redirect:/users";
    }
}
