package com.example.mtc.mapper;

import com.example.mtc.model.LoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper {
  int deleteByPrimaryKey(Long loginId);

  int insert(LoginLog record);

  int insertSelective(LoginLog record);

  LoginLog selectByPrimaryKey(Long loginId);

  int updateByPrimaryKeySelective(LoginLog record);

  int updateByPrimaryKey(LoginLog record);
}