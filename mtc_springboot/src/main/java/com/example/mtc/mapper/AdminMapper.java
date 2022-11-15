package com.example.mtc.mapper;

import com.example.mtc.model.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("ALL")
public interface AdminMapper {
  int deleteByPrimaryKey(Long adminId);

  int insert(Admin record);

  int insertSelective(Admin record);

  Admin selectByPrimaryKey(Long adminId);

  int updateByPrimaryKeySelective(Admin record);

  int updateByPrimaryKey(Admin record);
}