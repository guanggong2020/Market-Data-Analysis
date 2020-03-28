package com.example.demo.controller;

import com.example.demo.dao.USAStockDataDao;
import com.example.demo.entities.USAStockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/USA_stock_data")
public class USAStockDataController {

    @Autowired
    private USAStockDataDao usaStockDataDao;

    @RequestMapping("/findDataByCodeOrName/{input}/{pageNum}/{pageSize}")
    public List<USAStockData> find(@PathVariable("input") String input, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(input, pageNum, pageSize);
        return dataList;
    }

    @RequestMapping("/findDataByCodeOrName/{input}/{fromDate}/{toDate}/{pageNum}/{pageSize}")
    public List<USAStockData> findByDate(@PathVariable("input") String input, @PathVariable String fromDate, @PathVariable String toDate, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(input, fromDate, toDate, pageNum, pageSize);
        return dataList;
    }

    @RequestMapping("/findDataByRegex/{input}/{selection}/{pageNum}/{pageSize}")
    public List<USAStockData> findByRegex(@PathVariable("input") String input, @PathVariable("selection") String selection, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<USAStockData> dataList = usaStockDataDao.findDataByRegex(input, selection, pageNum, pageSize);
        return dataList;
    }
}
