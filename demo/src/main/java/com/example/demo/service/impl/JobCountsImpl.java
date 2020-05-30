package com.example.demo.service.impl;

import com.example.demo.dao.JobCountsDao;
import com.example.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobCountsImpl implements JobCountsDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 用于统计职位热度
     */
    @Override
    public List<SecondTypeCount> secondTypeCount() {
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("count")));
        return mongoTemplate.find(query, SecondTypeCount.class, "secondTypeCount");
    }

    /**
     * 用于统计薪资热度
     */
    @Override
    public List<SalaryCount> salaryCount() {
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("count")));
        return mongoTemplate.find(query, SalaryCount.class, "salaryCount");
    }

    /**
     * 用于统计城市热度
     */
    @Override
    public List<CityCount> cityCount() {
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("count")));
        return mongoTemplate.find(query, CityCount.class, "cityCount");
    }

    /**
     * 用于统计教育热度
     */
    @Override
    public List<EducationCount> educationCount() {
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("count")));
        return mongoTemplate.find(query, EducationCount.class, "educationCount");
    }

    /**
     * 用于统计职位数量
     */
    @Override
    public Long jobCount() {
        return mongoTemplate.count(new Query(), Job.class, "jobs");
    }
}
