package com.example.demo.service.impl;

import com.example.demo.dao.GuPiaoDataDao;
import com.example.demo.entities.GuPiaoData;

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
public class GuPiaoDataDaoImpl implements GuPiaoDataDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 用于精确查询
     */
    @Override
    public List<GuPiaoData> findDataByCodeOrName(String input, Integer pageNum, Integer pageSize) {
        if (input == null || input.trim().length() == 0)
            return new ArrayList<GuPiaoData>();
        Query query= new Query();
        if (input.matches("\\d+"))
            query.addCriteria(Criteria.where("code").is(input));
        else
            query.addCriteria(Criteria.where("name").is(input));
        query.with(Sort.by(Sort.Order.desc("date")));
        long recordTotal = mongoTemplate.count(query, GuPiaoData.class, "gupiao_data");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        pageNum = pageNum > pageTotal ? pageTotal : pageNum;
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, GuPiaoData.class, "gupiao_data");
    }

    /**
     * 用于精确查询（自定义时间范围）
     */
    @Override
    public List<GuPiaoData> findDataByCodeOrName(String input, String fromDate, String toDate, Integer pageNum, Integer pageSize) {
        if (input == null || input.trim().length() == 0)
            return new ArrayList<GuPiaoData>();
        Query query= new Query();
        if (input.matches("\\d+"))
            query.addCriteria(Criteria.where("code").is(input));
        else
            query.addCriteria(Criteria.where("name").is(input));

        query.addCriteria(Criteria.where("date").gte(fromDate).lte(toDate));
        query.with(Sort.by(Sort.Order.desc("date")));
        long recordTotal = mongoTemplate.count(query, GuPiaoData.class, "gupiao_data");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        pageNum = pageNum > pageTotal ? pageTotal : pageNum;
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, GuPiaoData.class, "gupiao_data");
    }

    /**
     * 用于模糊查询
     */
    @Override
    public List<GuPiaoData> findDataByRegex(String input, String selection, Integer pageNum, Integer pageSize) {
        Query query = new Query();
        if (input != null && input.length() > 0) {
            Pattern pattern = Pattern.compile("^.*" + input + ".*$", Pattern.CASE_INSENSITIVE);
            if (input.matches("\\d+"))
                query.addCriteria(Criteria.where("code").regex(pattern));
            else
                query.addCriteria(Criteria.where("name").regex(pattern));
        }
        else
            query.addCriteria(Criteria.where("").is(input));

        if (selection == null || selection.length() == 0)
            selection = "comprehensive";

        if (selection.equals("comprehensive"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("closingPrice"), Sort.Order.desc("change"), Sort.Order.desc("quoteChange")));
        else if (selection.equals("closingPriceRise"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.asc("closingPrice")));
        else if (selection.equals("closingPriceDrop"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("closingPrice")));
        else if (selection.equals("changeRise"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.asc("change")));
        else if (selection.equals("changeDrop"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("change")));
        else if (selection.equals("quoteChangeRise"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.asc("quoteChange")));
        else if (selection.equals("quoteChangeDrop"))
            query.with(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("quoteChange")));

        long recordTotal = mongoTemplate.count(query, GuPiaoData.class, "gtd");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        pageNum = pageNum > pageTotal ? pageTotal : pageNum;
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, GuPiaoData.class, "gtd");
    }

    /**
     * 用于统计股票数量
     */
    @Override
    public Long guPiaoCount() {
        return mongoTemplate.count(new Query(), GuPiaoData.class, "gtd");
    }
}
