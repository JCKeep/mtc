package com.example.mtc.mapper;

import com.example.mtc.model.Food;

public interface FoodMapper {
    int deleteByPrimaryKey(Long foodId);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Long foodId);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKeyWithBLOBs(Food record);

    int updateByPrimaryKey(Food record);
}