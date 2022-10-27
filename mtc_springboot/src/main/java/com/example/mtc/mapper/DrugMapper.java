package com.example.mtc.mapper;

import com.example.mtc.model.Drug;

public interface DrugMapper {
    int deleteByPrimaryKey(Long drugId);

    int insert(Drug record);

    int insertSelective(Drug record);

    Drug selectByPrimaryKey(Long drugId);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKeyWithBLOBs(Drug record);

    int updateByPrimaryKey(Drug record);
}