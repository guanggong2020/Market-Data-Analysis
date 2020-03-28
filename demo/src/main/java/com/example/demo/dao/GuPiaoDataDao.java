package com.example.demo.dao;

import com.example.demo.entities.GuPiaoData;

import java.util.List;

public interface GuPiaoDataDao {
    List<GuPiaoData> findDataByCodeOrName(String input, Integer pageNum, Integer pageSize);
    List<GuPiaoData> findDataByCodeOrName(String input, String fromDate, String toDate, Integer pageNum, Integer pageSize);
    List<GuPiaoData> findDataByRegex(String input, String selection, Integer pageNum, Integer pageSize);
    Long guPiaoCount();
}