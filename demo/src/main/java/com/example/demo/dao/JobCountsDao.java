package com.example.demo.dao;

import com.example.demo.entities.CityCount;
import com.example.demo.entities.EducationCount;
import com.example.demo.entities.SalaryCount;
import com.example.demo.entities.SecondTypeCount;

import java.util.List;

public interface JobCountsDao {
    List<SecondTypeCount> secondTypeCount();
    List<SalaryCount> salaryCount();
    List<CityCount> cityCount();
    List<EducationCount> educationCount();
    Long jobCount();
}
