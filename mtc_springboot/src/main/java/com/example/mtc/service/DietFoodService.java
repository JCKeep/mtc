package com.example.mtc.service;

import com.example.mtc.model.NewFood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DietFoodService {
  int deleteByPrimaryKey(Long foodId);

  int insert(NewFood record);

  int insertSelective(NewFood record);

  NewFood selectByPrimaryKey(Long foodId);

  List<NewFood> selectByNameWithNull(@Param("food_name") String name);

  List<NewFood> selectByName(@Param("food_name") String name);

  List<NewFood> selectInRangeWithNull(@Param("left") Long l, @Param("right") Long r);

  List<NewFood> selectInRange(@Param("left") Long l, @Param("right") Long r);

  int updateByPrimaryKeySelective(NewFood record);

  int updateByPrimaryKeyWithBLOBs(NewFood record);

  int updateByPrimaryKey(NewFood record);
}
