package com.example.mtc.mapper;

import com.example.mtc.model.Chat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
    int deleteByPrimaryKey(Long chatId);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(Long chatId);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKeyWithBLOBs(Chat record);

    int updateByPrimaryKey(Chat record);
}