package com.example.mtc.mapper;

import com.example.mtc.model.DietRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DietRecordMapper {
  int deleteByPrimaryKey(Long dietId);

  int insert(DietRecord record);

  int insertSelective(DietRecord record);

  DietRecord selectByPrimaryKey(Long dietId);

  int updateByPrimaryKeySelective(DietRecord record);

  int updateByPrimaryKey(DietRecord record);
}