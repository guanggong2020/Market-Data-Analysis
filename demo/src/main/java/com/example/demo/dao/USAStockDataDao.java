package com.example.demo.dao;

import com.example.demo.entities.USAStockData;

import java.util.List;

public interface USAStockDataDao {
    List<USAStockData> findDataByCodeOrName(String input, Integer pageNum, Integer pageSize);
    List<USAStockData> findDataByCodeOrName(String input, String fromDate, String toDate, Integer pageNum, Integer pageSize);
    List<USAStockData> findDataByRegex(String input, String selection, Integer pageNum, Integer pageSize);
    Long guPiaoCount();
}
