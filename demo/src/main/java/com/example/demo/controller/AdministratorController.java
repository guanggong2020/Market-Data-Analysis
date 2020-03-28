package com.example.demo.controller;

import com.example.demo.entities.Administrator;
import com.example.demo.mapper.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdministratorController {

    @Autowired
    private AdministratorMapper administratorMapper;

    //查询所有用户返回列表页面
    @GetMapping("/administrators")
    public String list(Model model){
        List<Administrator> administrators = administratorMapper.getAdministrator();
        //放在请求域中
        model.addAttribute("administrators", administrators);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "administrator/list";
    }

    //来到员工添加页面
    @GetMapping("/administrator/add")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        return "administrator/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/administrator/add")
    public String addOrUpdateUser(Administrator administrator){
        //来到员工列表页面
        System.out.println("保存的管理员信息：" + administrator);
        //保存员工
        Administrator a = administratorMapper.getAdministratorById(administrator.getId());
        if (a == null)
            administratorMapper.insertAdministrator(administrator);
        else
            administratorMapper.updateAdministrator(administrator);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/administrators";
    }

    // 来到修改页面，查出当前管理员，在页面回显
    @GetMapping("/administrator/edit/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Administrator administrator = administratorMapper.getAdministratorById(id);
        model.addAttribute("administrator", administrator);
        return "administrator/add";
    }

    // 管理员删除
    @GetMapping("/administrator/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        administratorMapper.deleteAdministratorById(id);
        return "redirect:/administrators";
    }
}
