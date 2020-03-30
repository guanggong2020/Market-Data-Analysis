package com.example.demo.controller;

import com.example.demo.dao.ShangZhengShenZhengDataDao;
import com.example.demo.entities.ShangZhengShenZhengData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shangzheng_shenzheng_data")
public class ShangZhengShenZhengDataController {

    @Autowired
    private ShangZhengShenZhengDataDao shangZhengShenZhengDataDao;

    @RequestMapping("/findDataByCodeOrName/{input}/{pageNum}/{pageSize}")
    public List<ShangZhengShenZhengData> find(@PathVariable("input") String input, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(input, pageNum, pageSize);
        return dataList;
    }

    @RequestMapping("/findDataByCodeOrName/{input}/{fromDate}/{toDate}/{pageNum}/{pageSize}")
    public List<ShangZhengShenZhengData> findByDate(@PathVariable("input") String input, @PathVariable String fromDate, @PathVariable String toDate, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(input, fromDate, toDate, pageNum, pageSize);
        return dataList;
    }

    @RequestMapping("/findDataByRegex/{input}/{selection}/{pageNum}/{pageSize}")
    public List<ShangZhengShenZhengData> findByRegex(@PathVariable("input") String input, @PathVariable("selection") String selection, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, Map<String, Object> map, HttpSession session) {
        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByRegex(input, selection, pageNum, pageSize);
        return dataList;
    }
}
