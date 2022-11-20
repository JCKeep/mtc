package com.example.mtc.mapper;

import com.example.mtc.model.DietRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
@SuppressWarnings("ALL")
public interface DietRecordMapper {
  int deleteByPrimaryKey(Long dietId);

  int insert(DietRecord record);

  int insertSelective(DietRecord record);

  DietRecord selectByPrimaryKey(Long dietId);

  int updateByPrimaryKeySelective(DietRecord record);

  int updateByPrimaryKey(DietRecord record);

  List<DietRecord> selectByUserIdAndDate(Long userId, Date start, Date end);
}