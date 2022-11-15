package com.example.mtc.mapper;

import com.example.mtc.model.Drug;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrugMapper {
  int deleteByPrimaryKey(Long drugId);

  int insert(Drug record);

  int insertSelective(Drug record);

  Drug selectByPrimaryKey(Long drugId);

  List<Drug> selectByNameWithNull(@Param("drug_name") String name);

  List<Drug> selectByName(@Param("drug_name") String name);

  List<Drug> selectInRangeWithNull(@Param("left") Long l, @Param("right") Long r);

  List<Drug> selectInRange(@Param("left") Long l, @Param("right") Long r);

  int updateByPrimaryKeySelective(Drug record);

  int updateByPrimaryKeyWithBLOBs(Drug record);

  int updateByPrimaryKey(Drug record);
}