package com.example.demo.dao;

import com.example.demo.entities.USAFundData;

import java.util.List;

public interface USAFundDataDao {
    List<USAFundData> findDataByCodeOrName(String input, Integer pageNum, Integer pageSize);
    List<USAFundData> findDataByCodeOrName(String input, String fromDate, String toDate, Integer pageNum, Integer pageSize);
    List<USAFundData> findDataByRegex(String input, String selection, Integer pageNum, Integer pageSize);
    Long jiJinCount();
    Long recordTotal(String input, String selection, Integer pageNum, Integer pageSize);
}
