package com.example.mtc.mapper;

import com.example.mtc.model.HealthSuggest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("ALL")
public interface HealthSuggestMapper {
  int deleteByPrimaryKey(Long suggestId);

  int insert(HealthSuggest record);

  int insertSelective(HealthSuggest record);

  HealthSuggest selectByPrimaryKey(Long suggestId);

  int updateByPrimaryKeySelective(HealthSuggest record);

  int updateByPrimaryKeyWithBLOBs(HealthSuggest record);

  int updateByPrimaryKey(HealthSuggest record);
}