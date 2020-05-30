package com.example.demo.controller;

import com.example.demo.dao.JobCountsDao;
import com.example.demo.entities.CityCount;
import com.example.demo.entities.EducationCount;
import com.example.demo.entities.SalaryCount;
import com.example.demo.entities.SecondTypeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobCountsController {

    @Autowired
    private JobCountsDao jobCountsDao;

    @RequestMapping("/secondTypeCount")
    public List<SecondTypeCount> secondTypeCount() {
        List<SecondTypeCount> dataList = jobCountsDao.secondTypeCount();
        return dataList;
    }

    @RequestMapping("/salaryCount")
    public List<SalaryCount> salaryCount() {
        List<SalaryCount> dataList = jobCountsDao.salaryCount();
        return dataList;
    }

    @RequestMapping("/cityCount")
    public List<CityCount> cityCount() {
        List<CityCount> dataList = jobCountsDao.cityCount();
        return dataList;
    }

    @RequestMapping("/educationCount")
    public List<EducationCount> educationCount() {
        List<EducationCount> dataList = jobCountsDao.educationCount();
        return dataList;
    }

    @RequestMapping("/jobCount")
    public Long jobCount() {
        return jobCountsDao.jobCount();
    }
}
