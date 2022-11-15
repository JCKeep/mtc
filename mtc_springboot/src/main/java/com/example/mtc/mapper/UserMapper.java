package com.example.mtc.mapper;

import com.example.mtc.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@SuppressWarnings("ALL")
@Mapper
public interface UserMapper {
  int deleteByPrimaryKey(Long userId);

  int insert(User record);

  int insertSelective(User record);

  User selectByPrimaryKey(Long userId);

  int updateByPrimaryKeySelective(User record);

  int updateByPrimaryKeyWithBLOBs(User record);

  int updateByPrimaryKey(User record);

  List<User> selectAll();

  User selectByEmail(String email);
}