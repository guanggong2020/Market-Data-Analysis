package com.example.demo.dao;

import com.example.demo.entities.Job;

import java.util.List;

public interface JobDao {
    List<Job> findDataByRegex(String input, Integer pageNum, Integer pageSize);
    Long jobCount();
}
