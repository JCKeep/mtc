package com.example.mtc.mapper;

import com.example.mtc.model.loginLog;

public interface loginLogMapper {
    int deleteByPrimaryKey(Long loginId);

    int insert(loginLog record);

    int insertSelective(loginLog record);

    loginLog selectByPrimaryKey(Long loginId);

    int updateByPrimaryKeySelective(loginLog record);

    int updateByPrimaryKey(loginLog record);
}