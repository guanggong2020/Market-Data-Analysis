package com.example.demo.controller;

import com.example.demo.dao.USAFundDataDao;
import com.example.demo.entities.USAFundData;
import com.example.demo.entities.USAStockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/USA_fund_data")
public class USAFundDataController {

    @Autowired
    private USAFundDataDao usaFundDataDao;

    @RequestMapping("/findDataByCodeOrName/{input}/{pageNum}/{pageSize}")
    public List<USAFundData> find(@PathVariable("input") String input, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<USAFundData> dataList = usaFundDataDao.findDataByCodeOrName(input, pageNum, pageSize);
        return dataList;
    }

    @RequestMapping("/findDataByCodeOrName/{input}/{fromDate}/{toDate}/{pageNum}/{pageSize}")
    public List<USAFundData> findByDate(@PathVariable("input") String input, @PathVariable String fromDate, @PathVariable String toDate, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<USAFundData> dataList = usaFundDataDao.findDataByCodeOrName(input, fromDate, toDate, pageNum, pageSize);
        return dataList;
    }

    @RequestMapping("/findDataByRegex/{input}/{selection}/{pageNum}/{pageSize}")
    public List<USAFundData> findByRegex(@PathVariable("input") String input, @PathVariable("selection") String selection, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<USAFundData> dataList = usaFundDataDao.findDataByRegex(input, selection, pageNum, pageSize);
        return dataList;
    }
}
