package com.example.mtc.mapper;

import com.example.mtc.model.loginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface loginLogMapper {
    int deleteByPrimaryKey(Long loginId);

    int insert(loginLog record);

    int insertSelective(loginLog record);

    loginLog selectByPrimaryKey(Long loginId);

    int updateByPrimaryKeySelective(loginLog record);

    int updateByPrimaryKey(loginLog record);
}