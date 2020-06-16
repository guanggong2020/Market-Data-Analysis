package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.entities.CityCount;
import com.example.demo.entities.EducationCount;
import com.example.demo.entities.SalaryCount;
import com.example.demo.entities.SecondTypeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class JobListController {

    @Autowired
    private JobCountsDao jobCountsDao;

    @GetMapping("/job")
    public String list(Model model) {
        List<SecondTypeCount> secondTypeCounts = jobCountsDao.secondTypeCount();
        List<SalaryCount> salaryCounts = jobCountsDao.salaryCount();
        List<CityCount> cityCounts = jobCountsDao.cityCount();
        List<EducationCount> educationCounts = jobCountsDao.educationCount();
        Long jobCount = jobCountsDao.jobCount();
        model.addAttribute("secondTypeCounts", secondTypeCounts);
        model.addAttribute("salaryCounts", salaryCounts);
        model.addAttribute("cityCounts", cityCounts);
        model.addAttribute("educationCounts", educationCounts);
        model.addAttribute("jobCount", jobCount);
        return "job/list";
    }
}
