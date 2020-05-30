package com.example.demo.dao;

import com.example.demo.entities.ShangZhengShenZhengData;

import java.util.List;

public interface ShangZhengShenZhengDataDao {
    List<ShangZhengShenZhengData> findDataByCodeOrName(String input, Integer pageNum, Integer pageSize);
    List<ShangZhengShenZhengData> findDataByCodeOrName(String input, String fromDate, String toDate, Integer pageNum, Integer pageSize);
    List<ShangZhengShenZhengData> findDataByRegex(String input, String selection, Integer pageNum, Integer pageSize);
    Long ShangZhengCount();
    Long ShenZhengCount();
    Long recordTotal(String input, String selection, Integer pageNum, Integer pageSize);
}
