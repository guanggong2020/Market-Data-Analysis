package com.example.demo.controller;

import com.example.demo.dao.JobDao;
import com.example.demo.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobDao jobDao;

    @RequestMapping("/findDataByRegex/{input}/{pageNum}/{pageSize}")
    public List<Job> findByRegex(@PathVariable("input") String input, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<Job> dataList = jobDao.findDataByRegex(input, pageNum, pageSize);
        return dataList;
    }
}
