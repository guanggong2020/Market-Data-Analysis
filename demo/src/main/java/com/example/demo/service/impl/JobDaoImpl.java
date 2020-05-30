package com.example.demo.service.impl;

import com.example.demo.dao.JobDao;
import com.example.demo.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class JobDaoImpl implements JobDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 用于模糊查询
     */
    @Override
    public List<Job> findDataByRegex(String input, Integer pageNum, Integer pageSize) {
        Query query = new Query();
        if (input != null && input.length() > 0) {
            Pattern pattern = Pattern.compile("^.*" + input + ".*$", Pattern.CASE_INSENSITIVE);
            if (input.matches("\\d+"))
                query.addCriteria(Criteria.where("positionId").regex(pattern));
            else
                query.addCriteria(Criteria.where("secondType").regex(pattern));
        }

        long recordTotal = mongoTemplate.count(query, Job.class, "jobs");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        if (pageNum <= 0)
            pageNum = 1;
        else if (pageNum > pageTotal)
            return new ArrayList<Job>();
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, Job.class, "jobs");
    }

    /**
     * 用于统计职位数量
     */
    @Override
    public Long jobCount() {
        return mongoTemplate.count(new Query(), Job.class, "jobs");
    }
}
