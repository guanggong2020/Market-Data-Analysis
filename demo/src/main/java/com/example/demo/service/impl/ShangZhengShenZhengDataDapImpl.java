package com.example.demo.service.impl;

import com.example.demo.dao.ShangZhengShenZhengDataDao;
import com.example.demo.entities.JiJinData;
import com.example.demo.entities.ShangZhengShenZhengData;
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
public class ShangZhengShenZhengDataDapImpl implements ShangZhengShenZhengDataDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 用于精确查询
     */
    @Override
    public List<ShangZhengShenZhengData> findDataByCodeOrName(String input, Integer pageNum, Integer pageSize) {
        if (input == null || input.trim().length() == 0)
            return new ArrayList<ShangZhengShenZhengData>();

        String[] ss = input.split("\\+");
        Query query= new Query();

        if (ss.length == 1) {
            if (ss[0].matches("\\d+"))
                query.addCriteria(Criteria.where("code").is(ss[0]));
            else
                query.addCriteria(Criteria.where("name").is(ss[0]));
        }
        else if (ss.length == 2) {
            if (ss[0].equals("上证"))
                query.addCriteria(Criteria.where("type").is(1));
            else
                query.addCriteria(Criteria.where("type").is(2));

            if (ss[1].matches("\\d+"))
                query.addCriteria(Criteria.where("code").is(ss[1]));
            else
                query.addCriteria(Criteria.where("name").is(ss[1]));
        }

        query.with(Sort.by(Sort.Order.desc("date")));
        long recordTotal = mongoTemplate.count(query, ShangZhengShenZhengData.class, "shangzheng_shenzheng_data");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        if (pageNum <= 0)
            pageNum = 1;
        else if (pageNum > pageTotal)
            return new ArrayList<ShangZhengShenZhengData>();
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, ShangZhengShenZhengData.class, "shangzheng_shenzheng_data");
    }

    /**
     * 用于精确查询（自定义时间范围）
     */
    @Override
    public List<ShangZhengShenZhengData> findDataByCodeOrName(String input, String fromDate, String toDate, Integer pageNum, Integer pageSize) {
        if (input == null || input.trim().length() == 0)
            return new ArrayList<ShangZhengShenZhengData>();

        String[] ss = input.split("\\+");
        Query query= new Query();

        if (ss.length == 1) {
            if (ss[0].matches("\\d+"))
                query.addCriteria(Criteria.where("code").is(ss[0]));
            else
                query.addCriteria(Criteria.where("name").is(ss[0]));
        }
        else if (ss.length == 2) {
            if (ss[0].equals("上证"))
                query.addCriteria(Criteria.where("type").is(1));
            else
                query.addCriteria(Criteria.where("type").is(2));

            if (ss[1].matches("\\d+"))
                query.addCriteria(Criteria.where("code").is(ss[1]));
            else
                query.addCriteria(Criteria.where("name").is(ss[1]));
        }

        query.addCriteria(Criteria.where("date").gte(fromDate).lte(toDate));
        query.with(Sort.by(Sort.Order.desc("date")));
        long recordTotal = mongoTemplate.count(query, ShangZhengShenZhengData.class, "shangzheng_shenzheng_data");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        if (pageNum <= 0)
            pageNum = 1;
        else if (pageNum > pageTotal)
            return new ArrayList<ShangZhengShenZhengData>();
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, ShangZhengShenZhengData.class, "shangzheng_shenzheng_data");
    }

    /**
     * 用于模糊查询
     */
    @Override
    public List<ShangZhengShenZhengData> findDataByRegex(String input, String selection, Integer pageNum, Integer pageSize) {
        String[] ss = input.split("\\+");
        Query query = new Query();

        if (ss[0].equals("上证"))
            query.addCriteria(Criteria.where("type").is(1));
        else
            query.addCriteria(Criteria.where("type").is(2));

        if (ss.length == 2) {
            Pattern pattern = Pattern.compile("^.*" + ss[1] + ".*$", Pattern.CASE_INSENSITIVE);
            if (ss[1].matches("\\d+"))
                query.addCriteria(Criteria.where("code").regex(pattern));
            else
                query.addCriteria(Criteria.where("name").regex(pattern));
        }

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

        long recordTotal = mongoTemplate.count(query, ShangZhengShenZhengData.class, "zstd");
        int pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));  // 总页数
        System.out.println(pageTotal);
        if (pageNum <= 0)
            pageNum = 1;
        else if (pageNum > pageTotal)
            return new ArrayList<ShangZhengShenZhengData>();
        int offset = (pageNum - 1) * pageSize;
        query.skip(offset).limit(pageSize);  // 分页逻辑
        return mongoTemplate.find(query, ShangZhengShenZhengData.class, "zstd");
    }

    /**
     * 用于统计上证数量
     */
    @Override
    public Long ShangZhengCount() {
        return mongoTemplate.count(new Query(Criteria.where("type").is(1)), ShangZhengShenZhengData.class, "zstd");
    }

    /**
     * 用于统计深证数量
     */
    @Override
    public Long ShenZhengCount() {
        return mongoTemplate.count(new Query(Criteria.where("type").is(2)), ShangZhengShenZhengData.class, "zstd");
    }

    /**
     * 用于返回查询记录数
     */
    @Override
    public Long recordTotal(String input, String selection, Integer pageNum, Integer pageSize) {
        String[] ss = input.split("\\+");
        Query query = new Query();

        if (ss[0].equals("上证"))
            query.addCriteria(Criteria.where("type").is(1));
        else
            query.addCriteria(Criteria.where("type").is(2));

        if (ss.length == 2) {
            Pattern pattern = Pattern.compile("^.*" + ss[1] + ".*$", Pattern.CASE_INSENSITIVE);
            if (ss[1].matches("\\d+"))
                query.addCriteria(Criteria.where("code").regex(pattern));
            else
                query.addCriteria(Criteria.where("name").regex(pattern));
        }

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

        return mongoTemplate.count(query, ShangZhengShenZhengData.class, "zstd");
    }
}
