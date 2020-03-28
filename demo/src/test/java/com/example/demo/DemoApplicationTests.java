package com.example.demo;

import com.example.demo.entities.GuPiaoData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Test
    void test() {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("").is(""));
//        query.limit(100);
//
//        List<GuPiaoData> dataList = mongoTemplate.find(query, GuPiaoData.class, "gtd");
//
//        System.out.println(dataList.size());
//
//        for (GuPiaoData guPiaoData : dataList)
//            System.out.println(guPiaoData);
    }
}
