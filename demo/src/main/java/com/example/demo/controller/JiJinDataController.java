package com.example.demo.controller;

import com.example.demo.dao.JiJinDataDao;
import com.example.demo.entities.JiJinData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jijin_data")
public class JiJinDataController {

    @Autowired
    private JiJinDataDao jiJinDataDao;

    @RequestMapping("/findDataByCodeOrName/{input}/{pageNum}/{pageSize}")
    public List<JiJinData> find(@PathVariable("input") String input, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(input, pageNum, pageSize);
        return dataList;
    }

    @RequestMapping("/findDataByCodeOrName/{input}/{fromDate}/{toDate}/{pageNum}/{pageSize}")
    public List<JiJinData> findByDate(@PathVariable("input") String input, @PathVariable String fromDate, @PathVariable String toDate, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(input, fromDate, toDate, pageNum, pageSize);
        return dataList;
    }

    @RequestMapping("/findDataByRegex/{input}/{selection}/{pageNum}/{pageSize}")
    public List<JiJinData> findByRegex(@PathVariable("input") String input, @PathVariable("selection") String selection, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<JiJinData> dataList = jiJinDataDao.findDataByRegex(input, selection, pageNum, pageSize);
        return dataList;
    }
}
