package com.example.mtc.mapper;

import com.example.mtc.model.HealthRecord;

public interface HealthRecordMapper {
    int deleteByPrimaryKey(Long healthRecordId);

    int insert(HealthRecord record);

    int insertSelective(HealthRecord record);

    HealthRecord selectByPrimaryKey(Long healthRecordId);

    int updateByPrimaryKeySelective(HealthRecord record);

    int updateByPrimaryKey(HealthRecord record);
}