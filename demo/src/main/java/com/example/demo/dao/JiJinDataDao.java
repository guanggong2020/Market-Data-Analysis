package com.example.demo.dao;

import com.example.demo.entities.JiJinData;

import java.util.List;

public interface JiJinDataDao {
    List<JiJinData> findDataByCodeOrName(String input, Integer pageNum, Integer pageSize);
    List<JiJinData> findDataByCodeOrName(String input, String fromDate, String toDate, Integer pageNum, Integer pageSize);
    List<JiJinData> findDataByRegex(String input, String selection, Integer pageNum, Integer pageSize);
    Long jiJinCount();
}
