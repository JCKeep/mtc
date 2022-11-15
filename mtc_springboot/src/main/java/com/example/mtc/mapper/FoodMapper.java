package com.example.mtc.mapper;

import com.example.mtc.model.Drug;
import com.example.mtc.model.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@SuppressWarnings("ALL")
public interface FoodMapper {
  int deleteByPrimaryKey(Long foodId);

  int insert(Food record);

  int insertSelective(Food record);

  Food selectByPrimaryKey(Long foodId);

  List<Food> selectByNameWithNull(@Param("food_name") String name);

  List<Food> selectByName(@Param("food_name") String name);

  List<Food> selectInRangeWithNull(@Param("left") Long l, @Param("right") Long r);

  List<Food> selectInRange(@Param("left") Long l, @Param("right") Long r);

  int updateByPrimaryKeySelective(Food record);

  int updateByPrimaryKeyWithBLOBs(Food record);

  int updateByPrimaryKey(Food record);
}