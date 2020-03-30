package com.example.demo;

import com.example.demo.dao.GuPiaoDataDao;
import com.example.demo.entities.GuPiaoData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private GuPiaoDataDao guPiaoDataDao;

    @Test
    void test() {
        List<GuPiaoData> dataList = guPiaoDataDao.findDataByRegex(" ", "", 1, 100);

        System.out.println(dataList.size());

        for (GuPiaoData guPiaoData : dataList)
            System.out.println(guPiaoData);
    }
}
