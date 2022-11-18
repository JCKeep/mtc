package com.example.mtc.mapper;

import com.example.mtc.model.HealthRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
@SuppressWarnings("ALL")
public interface HealthRecordMapper {
  int deleteByPrimaryKey(Long healthRecordId);

  int deleteByUserId(Long userId, Date start, Date end);

  int insert(HealthRecord record);

  int insertSelective(HealthRecord record);

  HealthRecord selectByPrimaryKey(Long healthRecordId);

  List<HealthRecord> selectByUserId(Long userId, Date start, Date end);

  int updateByPrimaryKeySelective(HealthRecord record);

  int updateByUserIdSelective(HealthRecord record);

  int updateByPrimaryKey(HealthRecord record);
}