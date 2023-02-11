package com.example.mtc.mapper;

import com.example.mtc.model.Relation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@SuppressWarnings("ALL")
@Mapper
public interface RelationMapper {
  int deleteByPrimaryKey(Long relationId);

  int insert(Relation record);

  int insertSelective(Relation record);

  Relation selectByPrimaryKey(Long relationId);

  int updateByPrimaryKeySelective(Relation record);

  int updateByPrimaryKey(Relation record);

  Integer getUserCount(Long doctorId);

  Integer getBindingStatus(Long doctorId, Long userId);

  List<Long> getUsers(Long doctorId);

  void delete(Long userId, Long doctorId);
  void update(Long userId, Long doctorId, Integer value);

  void deleteRev(Long doctorId);

}