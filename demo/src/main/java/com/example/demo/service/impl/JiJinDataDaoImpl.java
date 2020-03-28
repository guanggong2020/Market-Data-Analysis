package com.example.demo.service.impl;

import com.example.demo.dao.JiJinDataDao;
import com.example.demo.entities.JiJinData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class JiJinDataDaoImpl implements JiJinDataDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 用于精确查询
     */
    @Override
    public List<JiJinData> findDataByCodeOrName(String input, Integer pageNum, Integer pageSize) {
        if (input == null || input.trim().length() == 0)
            return new ArrayList<JiJinData>();
        Query query= new Query();
        if (input.matches("\\d+"))
            query.addCriteria(Criteria.where("code").is(input));
        else
            query.addCriteria(Criteria.where("fundName").is(input));
        query.with(Sort.by(Sort.Order.desc("date")));
        long recordTotal = mongoTemplate.count(query, JiJinData.class, "jijin_data");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        pageNum = pageNum > pageTotal ? pageTotal : pageNum;
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, JiJinData.class, "jijin_data");
    }

    /**
     * 用于精确查询（自定义时间范围）
     */
    @Override
    public List<JiJinData> findDataByCodeOrName(String input, String fromDate, String toDate, Integer pageNum, Integer pageSize) {
        if (input == null || input.trim().length() == 0)
            return new ArrayList<JiJinData>();
        Query query= new Query();
        if (input.matches("\\d+"))
            query.addCriteria(Criteria.where("code").is(input));
        else
            query.addCriteria(Criteria.where("fundName").is(input));

        query.addCriteria(Criteria.where("date").gte(fromDate).lte(toDate));
        query.with(Sort.by(Sort.Order.desc("date")));
        long recordTotal = mongoTemplate.count(query, JiJinData.class, "jijin_data");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        pageNum = pageNum > pageTotal ? pageTotal : pageNum;
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, JiJinData.class, "jijin_data");
    }

    /**
     * 用于模糊查询
     */
    @Override
    public List<JiJinData> findDataByRegex(String input, String selection, Integer pageNum, Integer pageSize) {
        Query query = new Query();
        if (input != null && input.length() > 0) {
            Pattern pattern = Pattern.compile("^.*" + input + ".*$", Pattern.CASE_INSENSITIVE);
            if (input.matches("\\d+"))
                query.addCriteria(Criteria.where("code").regex(pattern));
            else
                query.addCriteria(Criteria.where("fundName").regex(pattern));
        }
        else
            query.addCriteria(Criteria.where("").is(input));

        if (selection == null || selection.length() == 0)
            selection = "comprehensive";

        if (selection.equals("comprehensive"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("unitNetWorth"), Sort.Order.desc("cumulativeNetWorth"), Sort.Order.desc("growthRate")));
        else if (selection.equals("unitNetWorthRise"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.asc("unitNetWorth")));
        else if (selection.equals("unitNetWorthDrop"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("unitNetWorth")));
        else if (selection.equals("cumulativeNetWorthRise"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.asc("cumulativeNetWorth")));
        else if (selection.equals("cumulativeNetWorthDrop"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("cumulativeNetWorth")));
        else if (selection.equals("growthRateRise"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.asc("growthRate")));
        else if (selection.equals("growthRateDrop"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("growthRate")));

        long recordTotal = mongoTemplate.count(query, JiJinData.class, "jtd");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        pageNum = pageNum > pageTotal ? pageTotal : pageNum;
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, JiJinData.class, "jtd");
    }

    /**
     * 用于统计股票数量
     */
    @Override
    public Long jiJinCount() {
        return mongoTemplate.count(new Query(), JiJinData.class, "jtd");
    }
}
